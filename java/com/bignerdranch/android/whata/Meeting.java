package com.bignerdranch.android.whata;

import java.util.Date;
import java.util.UUID;

/**
 * Created by KYOME on 2017-11-18.
 * 모임 모델을 정의
 */

public class Meeting
{
    private UUID mId;
    private String mTitle;
    private String mLocation;
    private String mDescription;
    private Date mDate;

    public Meeting(){
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
