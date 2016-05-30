package com.spicytown.service.Menu;

import com.spicytown.model.entity.Menu;

import java.util.List;

/**
 * Created by yunlongxu on 4/27/16.
 */
public interface MenuServices {
    public Menu findMenuById(long id);
    public List<Menu> findMenuByCategory(String category);
    public Menu findMenuByCategoryAndName(String category, String name);
    public long saveMenu(Menu menu);
    public String deleteMenuById(long id);

    // TODO list of menu by category  .... done
    // TODO save menu and return menuid .... done
    // TODO save menu need to check duplicate
    // TODO delete menu by id .... done
}
