package com.bignerdranch.android.whata.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.whata.Meeting;

import java.util.Date;
import java.util.UUID;

/**
 * Created by KYOME on 2017-12-26.
 */

public class MeetingCursorWrapper extends CursorWrapper {
    public MeetingCursorWrapper (Cursor cursor){
        super(cursor);
    }

    public Meeting getMeeting() {
        String uuidString = getString(getColumnIndex(MeetingDbSchema.MeetingTable.Cols.UUID));
        String title = getString(getColumnIndex(MeetingDbSchema.MeetingTable.Cols.TITLE));
        String location = getString(getColumnIndex(MeetingDbSchema.MeetingTable.Cols.LOCATION));
        String description = getString(getColumnIndex(MeetingDbSchema.MeetingTable.Cols.DESCRIPTION));
        long days = getLong(getColumnIndex(MeetingDbSchema.MeetingTable.Cols.DAYS));

        Meeting meeting = new Meeting(UUID.fromString(uuidString));
        meeting.setTitle(title);
        meeting.setLocation(location);
        meeting.setDescription(description);
        meeting.setDays(new Date(days));


        return meeting;
    }
}
