package com.spicytown.controller;

import com.spicytown.component.Encryption;
import com.spicytown.model.entity.Menu;
import com.spicytown.model.entity.User;
import com.spicytown.model.entity.UserOrder;
import com.spicytown.service.User.UserServices;
import com.spicytown.service.UserOrder.UserOrderServices;
import com.spicytown.utils.date.DateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    Encryption encryption;

    @Autowired
    UserServices userServices;

    @Autowired
    UserOrderServices userOrderServices;

    @RequestMapping("")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        User user = userServices.findUserAccountByName(username);
        String userRole = null;
        if(user != null ){
            userRole =user.getRole();
        }

        System.out.println(authorities);
        model.addAttribute("userName", username);
        model.addAttribute("userRole", userRole);
        System.out.println("username: " + username);
        addAdmin();

        System.out.println("current date: " + new DateData().getCurrentDate());
        System.out.println("current time: " + new DateData().getCurrentTime());

        return "index";
    }

    private void addAdmin(){
        User admin = userServices.findUserAccountByName("admin@gmail.com");
        if(admin == null){
            admin = new User();
            admin.setName("admin@gmail.com");
            admin.setPassword("admin");
            admin.setRole("ROLE_ADMIN");
            userServices.saveUserAccount(admin);
        }
    }

    @RequestMapping("/signUp")
    public String signUp(){
        return "signUp";
    }

    @RequestMapping("/signIn")
    public String signIn(){
        return "signIn";
    }

    //testing purpose
    @RequestMapping("/helloworld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "hello world");
        return "hello";
    }


    //For User Profile
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/profile")
    public String showOrderHistory(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username

        User user = userServices.findUserAccountByName(username);
        if(user == null){
            model.addAttribute("Error UserName", username);
            return "404";
        }else{
            //get user email
            model.addAttribute("userName", user.getName());

            List<Object> historyOrders = new ArrayList<>();

            List<UserOrder> userOrderList = userOrderServices.findOrderByUser(user);

            if(userOrderList.size() > 0){
                for(UserOrder order: userOrderList){
                    DateData dateData = new DateData();
                    Map<String, Object> orderMap= new HashMap<>();
                    orderMap.put("orderId", order.getId());
                    orderMap.put("pickupDate", dateData.dateFormatter(order.getRequiredPickupDate()));
                    orderMap.put("pickupTime", dateData.timeFormatter(order.getRequiredPickupTime()));
                    orderMap.put("orderStatus", order.getStatus());
                    orderMap.put("orderPrice", order.getTotalPrice());
                    orderMap.put("itemList", order.getItemList());
                    orderMap.put("orderDate", dateData.dateFormatter(order.getOrderDate()));
                    historyOrders.add((HashMap)orderMap);
                } //end of for loop


                System.out.println("order item id    " + historyOrders);
                model.addAttribute("historyOrders", historyOrders);

            } else{
                model.addAttribute("emptyOrder", "You don't have any history order.");
                System.out.println("You don't have any history order.");
            }

        }//end of else

        return "profile";
    }


}
