package com.bignerdranch.android.whata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.whata.database.MeetingBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by KYOME on 2017-11-18.
 */

public class MeetingManger {
    private static MeetingManger sMeetingManger;
    private List<Meeting> mMeetings;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static MeetingManger get(Context context) {
        if (sMeetingManger == null) {
            sMeetingManger = new MeetingManger(context);
        }
        return sMeetingManger;
    }



    private MeetingManger(Context context) {

        mContext = context.getApplicationContext();
//        mDatabase = new MeetingBaseHelper(mContext).getWritableDatabase();

        mMeetings = new ArrayList<>();

        for (int i = 0 ; i<100 ; i++) {
            Meeting meeting = new Meeting();
            meeting.setTitle("모임 #" + i);
            mMeetings.add(meeting);
        }
    }

    public List<Meeting> getMeetings() {
        return  mMeetings;
    }

    public Meeting getMeeting(UUID id) {
        for(Meeting meeting: mMeetings) {
            if(meeting.getId().equals(id)) {
                return meeting;
            }
        }
        return null;
    }

}
