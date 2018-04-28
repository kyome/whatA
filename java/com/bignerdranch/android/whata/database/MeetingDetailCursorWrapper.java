package com.bignerdranch.android.whata.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.whata.Meeting;
import com.bignerdranch.android.whata.MeetingDetail;

import java.util.Date;
import java.util.UUID;

/**
 * Created by KYOME on 2018-01-12.
 */

public class MeetingDetailCursorWrapper extends CursorWrapper {
    public MeetingDetailCursorWrapper(Cursor cursor){
        super(cursor);
    }
    //기존에 만들어지 Meeting객체에 null로 들어가있을 DaysTimes[]에 추가해서 Meeting객체 생성
    public MeetingDetail getMeetingDetail () {
        String uuidString = getString(getColumnIndex(MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID));
        int daysCode = getInt(getColumnIndex(MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSCODE));
        long daysTime = getLong(getColumnIndex(MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSTIME));

        MeetingDetail meetingDetail = new MeetingDetail();
        meetingDetail.setId(UUID.fromString(uuidString));
        meetingDetail.setDaysCode(daysCode);
        meetingDetail.setDaysTime(new Date(daysTime));
        return meetingDetail;
    }


}
