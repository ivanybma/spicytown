package com.spicytown.model.repository.ChefSchedule;

import com.spicytown.model.entity.ChefSchedule;
import com.spicytown.model.entity.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cheyikung on 5/1/16.
 */

@Repository
public interface ChefScheduleRepo extends CrudRepository<ChefSchedule, Long>, ChefScheduleCustom {
    public List<ChefSchedule> findByChefId(int chefId);

    public List<ChefSchedule> findByDate(String date);

    public List<ChefSchedule> findByChefIdAndDate(int chefId, String date);

    public List<ChefSchedule> findByStartTimeAndDate(int startTime, String date);

    public List<ChefSchedule> findByChefIdAndStartTimeAndDate(int chefId, int startTime, String date);

    public List<ChefSchedule> findByUserOrder(UserOrder userOrder);

    public List<ChefSchedule> findByStartTime(int startTime);

    public List<ChefSchedule> findByEndTime(int endTime);
}
