package com.spicytown.model.repository.Item;

import com.spicytown.model.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by cheyikung on 4/28/16.
 */

@Repository
public interface ItemRepo extends CrudRepository<Item, Long>, ItemCustom {
}
