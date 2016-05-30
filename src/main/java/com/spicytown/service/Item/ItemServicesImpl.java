package com.spicytown.service.Item;

import com.spicytown.model.entity.Item;
import com.spicytown.model.repository.Item.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cheyikung on 4/28/16.
 */
@Service
public class ItemServicesImpl implements ItemServices {
    @Autowired
    ItemRepo itemRepo;

    @Override
    public Item findOrderItemById(long id) {
        return itemRepo.findOne(id);
    }

    @Override
    public void addItem(Item item) {
        itemRepo.save(item);
    }
}
