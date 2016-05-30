package com.spicytown.service.UserOrder;

import com.spicytown.model.entity.User;
import com.spicytown.model.entity.UserOrder;

import java.util.List;

/**
 * Created by cheyikung on 4/28/16.
 */
public interface UserOrderServices {
    public List<UserOrder> findOrderByUser(User user);
    public UserOrder findOrderById(long id);
    public String saveOrder(UserOrder userOrder);
    public String deleteOrderById(long id);
    public String cancelOrderById(long id);
    public String deleteOrder(UserOrder userOrder);
    public String deleteAllOrder();
    public List<UserOrder> findUserOrderByDates(String startdate, String enddate);
    public List<UserOrder> findAllOrder();

}
