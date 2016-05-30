package com.spicytown.component;

import com.spicytown.controller.UserOrderController;
import com.spicytown.model.entity.ChefSchedule;
import com.spicytown.model.entity.UserOrder;
import com.spicytown.service.ChefSchedule.ChefScheduleServices;
import com.spicytown.service.UserOrder.UserOrderServices;
import com.spicytown.utils.date.DateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yunlongxu on 5/1/16.
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ChefScheduleServices chefScheduleServices;

    @Autowired
    UserOrderServices userOrderServices;

    //set open time every 10 sec since 6 to 21
    @Scheduled(fixedRate = 5000)
    public void setOpenTime() {
        if(new DateData().getCurrentTime() >= 360 && new DateData().getCurrentTime() <= 1260) {
            UserOrderController.setOpen(true);
            System.out.println("schedule task set open");
        }
    }

    //set close every 10 sec from 21:00 to 6:00
    @Scheduled(fixedRate = 5000)
    public void setCloseTime() {
        if((new DateData().getCurrentTime() >= 0 && new DateData().getCurrentTime() <= 360)|| (new DateData().getCurrentTime() >= 1260 && new DateData().getCurrentTime() <= 1440)) {
            UserOrderController.setOpen(false);
            System.out.println("schedule task set close");
        }
    }

    //check order status every 30 min from 6am to 9pm
    @Scheduled(fixedRate = 5000)
    public void checkOrderStatus() {
        if(new DateData().getCurrentTime() % 30 == 0 && UserOrderController.isIsOpen()){
            //set chef schedule
            int currentTime = new DateData().getCurrentTime();
            List<ChefSchedule> chefScheduleList = chefScheduleServices.getChefScheduleListByEndTime(currentTime);
            for(ChefSchedule chefSchedule: chefScheduleList){
                if(chefSchedule.getUserOrder()!=null) {
                    UserOrder userOrder = chefSchedule.getUserOrder();
                    userOrder.setStatus("Ready to be picked up");
                    userOrderServices.saveOrder(userOrder);
                }
            }
            chefScheduleList = chefScheduleServices.getChefScheduleListByStartTime(currentTime);
            for(ChefSchedule chefSchedule: chefScheduleList){
                if(chefSchedule.getUserOrder()!=null) {
                    UserOrder userOrder = chefSchedule.getUserOrder();
                    userOrder.setStatus("In progress");
                    userOrderServices.saveOrder(userOrder);
                }
            }
        }
    }

    //set date every 5 sec
    @Scheduled(fixedRate = 5000)
    public void setDate() {
        System.out.println("schedule task set current date");
        UserOrderController.setTodayDate(dateFormat.format(new Date()));
    }
}
