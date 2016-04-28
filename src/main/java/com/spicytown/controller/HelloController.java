package com.spicytown.controller;

import com.spicytown.entity.ItemCategory;
import com.spicytown.entity.MenuItem;
import com.spicytown.service.MenuItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

	@Autowired
	MenuItemServices menuItemServices;

	@RequestMapping(name = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public String create() {
		long itemId;
		try {
			MenuItem menuItem = new MenuItem();
			menuItem.setCategory("Drink");
			menuItem.setName("Coke");
			menuItemServices.saveMenuItem(menuItem);
			itemId = menuItem.getId();
		}
		catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "Item succesfully created with id = " + Long.toString(itemId);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("id") int id) {
		try {
			MenuItem menuItem = new MenuItem();
			menuItem.setId(id);
			menuItemServices.deleteMenuItem(menuItem);
		}
		catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "Item " + Integer.toString(id) + " succesfully deleted!";
	}
}