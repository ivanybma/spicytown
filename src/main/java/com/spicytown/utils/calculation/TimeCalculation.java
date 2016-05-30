package com.spicytown.utils.calculation;

import com.spicytown.utils.date.DateData;

/**
 * Created by yunlongxu on 5/1/16.
 */
public class TimeCalculation {

    private int startTime;
    private int endTime;
    private int requiredTime;
    private int totalPrepTime;
    private int slot;

    public TimeCalculation() {
        this.startTime = 0;
        this.endTime = 0;
        this.requiredTime = 0;
        this.totalPrepTime = 0;
        this.slot = 0;
    }

    // TODO check get start time and get end time algorithm
    public int getStartTime() {
        if (requiredTime != 0) {
            startTime = requiredTime - totalPrepTime;
            if(startTime % 30 != 0){
                startTime -= (startTime % 30);
            }
        } else {
            startTime = new DateData().getCurrentTime();
            if(startTime % 30 != 0){
                startTime = startTime + 30 - startTime % 30;
            }
        }
        return startTime;
    }

    public int getEndTime() {
        if (requiredTime != 0) {
            if (requiredTime % 30 != 0) {
                endTime = requiredTime - requiredTime % 30;
            } else {
                endTime = requiredTime;
            }
        } else {
            if ((startTime + totalPrepTime) % 30 != 0) {
                endTime = startTime + totalPrepTime + 30 - (startTime + totalPrepTime) % 30;
            } else {
                endTime = startTime + totalPrepTime;
            }
        }
        return endTime;
    }

    public void setRequiredTime(int requiredTime) {
        this.requiredTime = requiredTime;
    }

    public void setTotalPrepTime(int totalPrepTime) {
        this.totalPrepTime = totalPrepTime;
    }

    public int getSlot() {
        slot = (endTime - startTime) / 30;
        return slot;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getRequiredTime() {
        requiredTime = startTime + slot * 30;
        return requiredTime;
    }
}
