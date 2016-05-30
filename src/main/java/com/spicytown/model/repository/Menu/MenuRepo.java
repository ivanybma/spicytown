package com.spicytown.model.repository.Menu;

import com.spicytown.model.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yunlongxu on 4/27/16.
 */
@Repository
public interface MenuRepo extends CrudRepository<Menu, Long>,MenuCustom {
    public Menu findByName(String name);
    public List<Menu> findByCategory(String category);
    public Menu findByCategoryAndName(String category, String name);
}
