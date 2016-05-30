package com.spicytown.service.ChefSchedule;

import com.spicytown.model.entity.ChefSchedule;
import com.spicytown.model.entity.UserOrder;
import com.spicytown.model.repository.ChefSchedule.ChefScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheyikung on 5/1/16.
 */

@Service
public class ChefScheduleServicesImpl implements ChefScheduleServices {
    @Autowired
    ChefScheduleRepo chefScheduleRepo;

    private final int OPEN_TIME = 360;
    private final int CLOSE_TIME = 1080;

    @Override
    @Transactional
    public void createNewSchedule(int chefId, String date) {
        if (chefScheduleRepo.findByChefIdAndDate(chefId, date).size() == 0) {
            List<ChefSchedule> chefScheduleList = new ArrayList<ChefSchedule>();
            for (int i = OPEN_TIME; i < CLOSE_TIME; i += 30) {
                chefScheduleList.add(new ChefSchedule(chefId, i, i + 30, true, date, null));
            }
            chefScheduleRepo.save(chefScheduleList);
        }
    }

    @Override
    @Transactional
    public String setSchedule(int startTime, int slot, String date, UserOrder userOrder) {
        return schedule(startTime, slot, date, userOrder, 1, true);
    }

    @Override
    public String saveChefScheduleList(List<ChefSchedule> chefScheduleList) {
        chefScheduleRepo.save(chefScheduleList);
        return null;
    }

    @Override
    public List<ChefSchedule> getChefScheduleListByStartTime(int startTime) {
        return chefScheduleRepo.findByStartTime(startTime);
    }

    @Override
    public List<ChefSchedule> getChefScheduleListByEndTime(int endTime) {
        return chefScheduleRepo.findByEndTime(endTime);
    }

    private String schedule(int startTime, int slot, String date, UserOrder userOrder, int k, boolean insertSchedule){
        if(k >= 3){
            return "Chefs are not available";
        }
        if(k == 1 && chefScheduleRepo.findByDate(date).size() == 0) {
            createNewSchedule(1, date);
            createNewSchedule(2, date);
            createNewSchedule(3, date);
        }
        List<ChefSchedule> chefScheduleList = new ArrayList<ChefSchedule>();

        for(int i = 1; i <= 3; i++) {
            boolean isAvailable = true;
            chefScheduleList.clear();
            for (int j = startTime; j < startTime + (30 * slot); j += 30) {
                chefScheduleList.addAll(chefScheduleRepo.findByChefIdAndStartTimeAndDate(i, j, date));
            }
            if(chefScheduleList.size() == 0){
                isAvailable = false;
            }else {
                for (ChefSchedule chefSchedule : chefScheduleList) {
                    if (chefSchedule.isAvailable() == false) {
                        isAvailable = false;
                        break;
                    }
                }
            }
            if (isAvailable) {
                if(insertSchedule) {
                    for (ChefSchedule chefSchedule : chefScheduleList) {
                        chefSchedule.setAvailable(false);
                        chefSchedule.setUserOrder(userOrder);
                    }
                    chefScheduleRepo.save(chefScheduleList);
                }
                return null;
            }
        }
        return schedule(startTime - (k * 30), slot, date, userOrder, k++, insertSchedule);
    }

//    @Override
//    public String getScheduleStatus(int startTime, int slot, String date, boolean isPreOrder) {
//        return schedule(startTime, slot, date, 0l, 1, false, isPreOrder);
//    }

    @Override
    @Transactional
    public void deleteScheduleByUserOrder(UserOrder userOrder) {
        List<ChefSchedule> chefScheduleList = chefScheduleRepo.findByUserOrder(userOrder);
        for(ChefSchedule chefSchedule: chefScheduleList){
            chefSchedule.setAvailable(true);
            chefSchedule.setUserOrder(null);
        }
        chefScheduleRepo.save(chefScheduleList);
    }

    @Override
    public List<ChefSchedule> getChefScheduleByUserOrder(UserOrder userOrder) {
        return chefScheduleRepo.findByUserOrder(userOrder);
    }
}
