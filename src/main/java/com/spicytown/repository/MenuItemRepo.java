package com.spicytown.repository;

import com.spicytown.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cheyikung on 4/27/16.
 */

@Repository
@Transactional
public interface MenuItemRepo extends CrudRepository<MenuItem, String>, MenuItemCustom {
    public MenuItem findByName(String name);
}
