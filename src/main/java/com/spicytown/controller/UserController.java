package com.spicytown.controller;

import com.spicytown.component.SmtpMailSender;
import com.spicytown.model.entity.Item;
import com.spicytown.model.entity.User;
import com.spicytown.model.entity.UserOrder;
import com.spicytown.service.ChefSchedule.ChefScheduleServices;
import com.spicytown.service.Item.ItemServices;
import com.spicytown.service.Menu.MenuServices;
import com.spicytown.service.User.UserServices;
import com.spicytown.service.UserOrder.UserOrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.support.HandlerMethodInvocationException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheyikung on 5/2/16.
 */
@Controller
@RequestMapping("/userlogin")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServices userServices;

    @Autowired
    SmtpMailSender smtpMailSender;


    //find user
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseBody
//    public ModelAndView findUser(@ModelAttribute("user") User user) {
//        if (userServices.findUserAccountByNameAndPassword(user) != null) {
//            System.out.println("login success");
//            return new ModelAndView("hello", "message", "login success");
//        }
//        System.out.println("login failed");
//        return new ModelAndView("hello", "message", "login failed");
//    }

    //adduser
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(@ModelAttribute("user") User user) {
        System.out.println("add user, username: " + user.getName());
        if (userServices.findUserAccountByName(user.getName()) != null) {
            return "error: user exists";
        }
        user.setVerified(false);
        user.setRole("ROLE_USER");
        userServices.saveUserAccount(user);
        long id = user.getId();
        try {
            // send message body: Send the URL+ID (http://yourapp.com/confirm?id=0123) as an email using javamail to the user;
            smtpMailSender.send(user.getName(), "please verify your account", "<a href=http://localhost:8080/userlogin/verify/" + Long.toString(id) + ">Please click the link to activate your account</a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id != 0l) {
            return null;
        }
        return "error: user information cannot be saved into database";
    }

//
//    //User SignOut
//    @RequestMapping(value = "/signOut")
//    public String signOut(@ModelAttribute("user") User user, Model model) {
//
//        return "index";
//    }

    //test
    //don't use
    @RequestMapping("/getverify/{id}")
    @ResponseBody
    public void gerVerified(@PathVariable("id") long id) {
        User user = userServices.findUserAccountById(id);
        System.out.println(user.isVerified());
    }

    //for email verification purpose.
    //don't use
    @RequestMapping("/verify/{id}")
    public String verify(@PathVariable("id") long id) {
        userServices.verifyUserAccount(id);
        return "redirect:/signIn";
    }


    // for testing purpose
    // don't use
    @RequestMapping("/createusertest")
    @ResponseBody
    public String create(String name) {
        String userId = "";
        try {
            User user = new User(name, "abcderf", false, "USER");
            userServices.saveUserAccount(user);
            userId = String.valueOf(user.getId());
            smtpMailSender.send("vincent881229@gmail.com", "Test mail from spring", "Howdy");
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User successfully created with id = " + userId;
    }



}
