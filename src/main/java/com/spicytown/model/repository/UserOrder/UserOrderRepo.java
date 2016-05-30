package com.spicytown.model.repository.UserOrder;

import com.spicytown.model.entity.User;
import com.spicytown.model.entity.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cheyikung on 4/28/16.
 */
@Repository
public interface UserOrderRepo extends CrudRepository<UserOrder, Long>, UserOrderCustom {
    public List<UserOrder> findUserOrderByUser(User user);
    public List<UserOrder> findUserOrderByOrderDate(String orderDate);

}
