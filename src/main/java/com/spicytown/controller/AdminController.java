package com.spicytown.controller;

import com.spicytown.model.entity.User;
import com.spicytown.model.entity.UserOrder;
import com.spicytown.service.User.UserServices;
import com.spicytown.service.UserOrder.UserOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cheyikung on 5/2/16.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserServices userServices;

    //admin page
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("")
    public String adminPage(){
        return "admin";
    }

    //find admin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String adminLogin(Model model, @ModelAttribute("admin") User admin) {
        if(userServices.findUserAccountByNameAndPassword(admin) != null){
            model.addAttribute("message", "admin login success");
        }else{
            model.addAttribute("message", "admin login failed");
        }
        return "hello";
    }

    //add admin account
    //optionals
    @RequestMapping(value = "/addadmin/{name}/{password}", method = RequestMethod.GET)
    public String addAdmin(Model model, @PathVariable("name") String name, @PathVariable("password") String password) {
        if (userServices.findUserAccountByName(name) != null) {
            model.addAttribute("message", "add admin failed. admin name exists");
            return "hello";
        }
        User admin = new User();
        admin.setName(name);
        admin.setPassword(password);
        admin.setRole("ROLE_ADMIN");
        long id = userServices.saveUserAccount(admin);
        if (id != 0l) {
            model.addAttribute("message", "add admin successed.");
            return "hello";
        }
        model.addAttribute("message", "add admin failed. admin account is not saved to database");
        return "hello";
    }
}
