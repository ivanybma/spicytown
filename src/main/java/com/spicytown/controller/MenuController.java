package com.spicytown.controller;

import com.spicytown.model.entity.Menu;
import com.spicytown.service.Menu.MenuServices;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.sql.Blob;
import java.util.List;


/**
 * Created by cheyikung on 5/2/16.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuServices menuServices;

    @Autowired
    SessionFactory sessionFactory;

    //find menu by Category
    // return modelAndView with 4 lists
    @RequestMapping(name = "", method = RequestMethod.GET)
    public String findMenuByCategory(){

        return "menu";
    }


    //test
    //add menu item with picture
    @RequestMapping(value = "/addmenu", method = RequestMethod.POST)
    public String addMenu(@ModelAttribute Menu menu, @RequestParam("file") MultipartFile file, Model model) {
        System.out.println(menu.getName());
        System.out.println(menu.getCategory());
        System.out.println(file.getName());
        System.out.println(file.getContentType());

        Session session = sessionFactory.openSession();

        try {

            Blob blob = Hibernate.getLobCreator(session).createBlob(IOUtils.toByteArray(file.getInputStream()));
            menu.setFilename(file.getOriginalFilename());
            menu.setImage(blob);
            menu.setContentType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.close();



        System.out.println("menu pic bytearr: " + menu.getImage().toString());
        menuServices.saveMenu(menu);
        model.addAttribute("message", menu.getImage().toString());
//        if(menuServices.saveMenu(menu) != -1l){
//            return Long.toString(menuServices.saveMenu(menu));
//        }
        return "hello";
    }

//    //add menu item
//    //checks for duplication
//    //original work
//    @RequestMapping(value = "/addmenu", method = RequestMethod.POST)
//    @ResponseBody
//    public String addMenu(@RequestBody Menu menu) {
//        if(menuServices.saveMenu(menu) != -1l){
//            return Long.toString(menuServices.saveMenu(menu));
//        }
//        return "failed to add menu";
//    }

    //find menu item
    //return list of menu
    @RequestMapping("/findmenu/{category}")
    @ResponseBody
    public List<Menu> findMenuByCategory(@PathVariable("category") String category){
        return menuServices.findMenuByCategory(category);
    }

    @RequestMapping(value = "/deletemenu/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMenu(@PathVariable("id") long id){
        return menuServices.deleteMenuById(id);
    }

    //find menu item
    //return menu
    @RequestMapping("/findmenu/{category}/{name}")
    @ResponseBody
    public Menu findMenuByCategoryAndName(@PathVariable("category") String category, @PathVariable("name") String name) {
        return menuServices.findMenuByCategoryAndName(category, name);
    }


    @RequestMapping("/findmenuTest/{id}")
    public String showSampleMenu(@PathVariable("id") long id, Model model){

        Menu menu = menuServices.findMenuById(id);
        model.addAttribute("menu", menu);
        return "receiveMenu";
    }

}
