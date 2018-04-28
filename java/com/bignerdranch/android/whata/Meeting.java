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



    public Meeting(UUID id){
        mId = id;
    }

    public Meeting(){
        this(UUID.randomUUID());
        //테스트용 임시입력날짜
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

}
