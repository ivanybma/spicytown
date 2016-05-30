package com.spicytown.service.Item;

import com.spicytown.model.entity.Item;

/**
 * Created by cheyikung on 4/28/16.
 */
public interface ItemServices {
    public Item findOrderItemById(long id);
    public void addItem(Item item);
}
