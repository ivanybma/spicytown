package com.spicytown.service;

import com.spicytown.entity.MenuItem;
import com.spicytown.repository.MenuItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by cheyikung on 4/27/16.
 */

@Service
public class MenuItemServicesImpl implements MenuItemServices{
    @Autowired
    MenuItemRepo menuItemRepo;

    public void saveMenuItem(MenuItem menuItem){
        menuItemRepo.save(menuItem);
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        menuItemRepo.delete(menuItem);
    }


}
