package com.bignerdranch.android.whata;

import java.util.Date;
import java.util.UUID;

/**
 * Created by KYOME on 2018-01-16.
 */

public class MeetingDetail {
    private Date mDaysTime ;
    private int mDaysCode;
    private UUID mId;

    public Date getDaysTime() {
        return mDaysTime;
    }

    public void setDaysTime(Date daysTime) {
        mDaysTime = daysTime;
    }

    public int getDaysCode() {
        return mDaysCode;
    }

    public void setDaysCode(int daysCode) {
        mDaysCode = daysCode;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }
}
