package com.spicytown.service.Menu;

import com.spicytown.model.entity.Menu;
import com.spicytown.model.repository.Menu.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunlongxu on 4/27/16.
 */
@Service
public class MenuServicesImpl implements MenuServices {
    @Autowired
    MenuRepo menuRepo;


    @Override
    public Menu findMenuById(long id) {
        return menuRepo.findOne(id);
    }

    @Override
    public List<Menu> findMenuByCategory(String category) {
        return menuRepo.findByCategory(category);
    }

    @Override
    public Menu findMenuByCategoryAndName(String category, String name) {
        return menuRepo.findByCategoryAndName(category, name);
    }


    @Override
    public long saveMenu(Menu menu) {
        if(menuRepo.findByCategoryAndName(menu.getCategory(), menu.getName())!=null){
            return -1l;
        }
        menuRepo.save(menu);
        if(menu.getId() != 0l){
            return menu.getId();
        }
        return -1l;
    }

    @Override
    public String deleteMenuById(long id) {
        Menu menu = menuRepo.findOne(id);
        if(menu==null){
            return "menu not found in database";
        }
        menuRepo.delete(menu);
        return null;
    }
}
