package com.spicytown.service.ChefSchedule;

import com.spicytown.model.entity.ChefSchedule;
import com.spicytown.model.entity.UserOrder;

import java.util.List;

/**
 * Created by cheyikung on 5/1/16.
 */
public interface ChefScheduleServices {
    public void createNewSchedule(int chefId, String date);
    public String setSchedule(int startTime, int slot, String date, UserOrder userOrder);
//    public String getScheduleStatus(int startTime, int slot, String date, boolean isPreOrder);
    public String saveChefScheduleList(List<ChefSchedule> chefScheduleList);
    public List<ChefSchedule> getChefScheduleListByStartTime(int startTime);
    public List<ChefSchedule> getChefScheduleListByEndTime(int endTime);
    public void deleteScheduleByUserOrder(UserOrder userOrder);
    public List<ChefSchedule> getChefScheduleByUserOrder(UserOrder userOrder);
}
