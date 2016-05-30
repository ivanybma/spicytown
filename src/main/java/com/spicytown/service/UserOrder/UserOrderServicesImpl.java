package com.spicytown.service.UserOrder;

import com.spicytown.model.entity.ChefSchedule;
import com.spicytown.model.entity.User;
import com.spicytown.model.entity.UserOrder;
import com.spicytown.model.repository.ChefSchedule.ChefScheduleRepo;
import com.spicytown.model.repository.UserOrder.UserOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cheyikung on 4/28/16.
 */
@Service
public class UserOrderServicesImpl implements UserOrderServices {

    @Autowired
    UserOrderRepo userOrderRepo;

    @Autowired
    ChefScheduleRepo chefScheduleRepo;


    @Override
    public List<UserOrder> findOrderByUser(User user) {
        return userOrderRepo.findUserOrderByUser(user);
    }

    @Override
    public UserOrder findOrderById(long id) {
        return userOrderRepo.findOne(id);
    }

    @Override
    public String saveOrder(UserOrder userOrder) {
        userOrderRepo.save(userOrder);
        if(userOrder.getId() != 0l){
            return null;
        }
        return "unable to save userorder to database";
    }

    @Override
    @Transactional
    public String deleteOrderById(long id) {
        return deleteOrder(userOrderRepo.findOne(id));
    }

    @Override
    public String cancelOrderById(long id) {
        UserOrder userOrder = userOrderRepo.findOne(id);
        if(userOrder == null){
            return "order does not exists";
        }else if(userOrder.getStatus().equals("In progress")){
            return "order can't be cancelled because it is being processed.";
        }
        List<ChefSchedule> chefScheduleList = chefScheduleRepo.findByUserOrder(userOrder);
        for(ChefSchedule chefSchedule : chefScheduleList){
            chefSchedule.setAvailable(true);
            chefSchedule.setUserOrder(null);
            chefScheduleRepo.save(chefSchedule);
        }
        userOrder.setStatus("Cancelled");
        userOrderRepo.save(userOrder);
        return null;
    }


    @Override
    @Transactional
    public String deleteOrder(UserOrder userOrder) {
        long id = userOrder.getId();
        if(userOrderRepo.findOne(id) == null){
            return "order does not exists";
        }
        List<ChefSchedule> chefScheduleList = chefScheduleRepo.findByUserOrder(userOrder);
        for(ChefSchedule chefSchedule : chefScheduleList){
            chefSchedule.setAvailable(true);
            chefSchedule.setUserOrder(null);
            chefScheduleRepo.save(chefSchedule);
        }
        userOrderRepo.delete(userOrder);
        return null;
    }



    @Override
    @Transactional
    public String deleteAllOrder() {

        Iterable<UserOrder> userOrderIterable = userOrderRepo.findAll();
        for(UserOrder userOrder : userOrderIterable){
            List<ChefSchedule> chefScheduleList = chefScheduleRepo.findByUserOrder(userOrder);
            for(ChefSchedule chefSchedule : chefScheduleList){
                chefSchedule.setAvailable(true);
                chefSchedule.setUserOrder(null);
                chefScheduleRepo.save(chefSchedule);
            }
        }
        userOrderRepo.deleteAll();
        return null;
    }

    @Override
    @Transactional
    public List<UserOrder> findUserOrderByDates(String startdate, String enddate) {
        List<UserOrder> userOrderList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = (Date) sdf.parse(startdate);
            date2 = (Date) sdf.parse(enddate);
            while (date1.compareTo(date2) <= 0) {
                userOrderList.addAll(userOrderRepo.findUserOrderByOrderDate(startdate));
                startdate = dateIncrement(startdate);
                date1 = (Date) sdf.parse(startdate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userOrderList;
    }

    @Override
    public List<UserOrder> findAllOrder() {
        Iterable<UserOrder> userOrderIterable = userOrderRepo.findAll();
        List<UserOrder> userOrderList = new ArrayList<>();
        for(UserOrder userOrder : userOrderIterable){
            userOrderList.add(userOrder);
        }
        return userOrderList;
    }

    private String dateIncrement(String startdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(startdate));
        c.add(Calendar.DATE, 1);  // number of days to add
        return sdf.format(c.getTime()).toString();
    }
}
