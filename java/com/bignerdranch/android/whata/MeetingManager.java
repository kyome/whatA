package com.bignerdranch.android.whata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.whata.database.MeetingBaseHelper;
import com.bignerdranch.android.whata.database.MeetingCursorWrapper;
import com.bignerdranch.android.whata.database.MeetingDbSchema;
import com.bignerdranch.android.whata.database.MeetingDetailCursorWrapper;
import com.bignerdranch.android.whata.database.MeetingDetailDbSchema;
import com.bignerdranch.android.whata.database.MeetingDetailHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by KYOME on 2017-11-18.
 */

public class MeetingManager {
    private static MeetingManager sMeetingManager;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteDatabase mDetailDatabase;
    public static MeetingManager get(Context context) {
        if (sMeetingManager == null) {
            sMeetingManager = new MeetingManager(context);
        }
        return sMeetingManager;
    }

    private MeetingManager(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new MeetingBaseHelper(mContext).getWritableDatabase();
        mDetailDatabase = new MeetingDetailHelper(mContext).getWritableDatabase();
    }
    //디테일은 여러개이기 때문에 배열로 받아서 for문으로 한번에 추가
    public void addMeeting(Meeting meeting, MeetingDetail[] meetingDetails) {
        ContentValues values = getContentValues(meeting);
        mDatabase.insert(MeetingDbSchema.MeetingTable.NAME,null,values);

        for (MeetingDetail meetingDetail : meetingDetails) {
            if (meetingDetail != null) {
                ContentValues detailValues = getDetailContentValues(meetingDetail);
                mDetailDatabase.insert(MeetingDetailDbSchema.MeetingDetailTable.NAME, null, detailValues);
            }
        }

    }

    public List<Meeting> getMeetings() {
        List<Meeting> meetings = new ArrayList<>();

        MeetingCursorWrapper cursor = queryMeetings(null,null);


        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                meetings.add(cursor.getMeeting());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return  meetings;
    }

    public List<MeetingDetail> getMeetingDetails(UUID meetingId) {
        List<MeetingDetail> meetingDetails = new ArrayList<>();
        MeetingDetailCursorWrapper detailCursor = queryMeetingsDetail(MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID + "=?",new String [] {meetingId.toString()});

        try {
            detailCursor.moveToFirst();
            while (!detailCursor.isAfterLast()) {
                meetingDetails.add(detailCursor.getMeetingDetail());
                detailCursor.moveToNext();
            }
        } finally {
            detailCursor.close();
        }

        return  meetingDetails;
    }

    public Meeting getMeeting(UUID id) {
        MeetingCursorWrapper cursor = queryMeetings(MeetingDbSchema.MeetingTable.Cols.UUID + " = ?", new String[] {id.toString()});


        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMeeting();
        } finally {
            cursor.close();
        }
    }

    public MeetingDetail getMeetingDetail(UUID id,int code) {
       //조건 두개 추가 필요
        MeetingDetailCursorWrapper cursor = queryMeetingsDetail(MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID + "=? AND "
                +MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSCODE+ "=? ",new String [] {id.toString() ,Integer.toString(code) });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMeetingDetail();
        } finally {
            cursor.close();
        }
    }


    public void updateMeeting(Meeting meeting) throws Exception {
            String uuidString = meeting.getId().toString();
            ContentValues values = getContentValues(meeting);

            mDatabase.update(MeetingDbSchema.MeetingTable.NAME, values, MeetingDbSchema.MeetingTable.Cols.UUID + "= ?", new String[] { uuidString});
    }


    private static ContentValues getContentValues( Meeting meeting) {
        ContentValues values = new ContentValues();
        values.put(MeetingDbSchema.MeetingTable.Cols.UUID, meeting.getId().toString());
        values.put(MeetingDbSchema.MeetingTable.Cols.TITLE,meeting.getTitle());
        values.put(MeetingDbSchema.MeetingTable.Cols.LOCATION, meeting.getLocation());
        values.put(MeetingDbSchema.MeetingTable.Cols.DESCRIPTION, meeting.getDescription());

        return values;
    }


    private static ContentValues getDetailContentValues(MeetingDetail meetingDetail) {
        ContentValues values = new ContentValues();
        values.put(MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID,meetingDetail.getId().toString());
        values.put(MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSCODE,meetingDetail.getDaysCode());
        values.put(MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSTIME, meetingDetail.getDaysTime().toString());

        return values;
    }

    private MeetingCursorWrapper queryMeetings(String whereClause, String [] whereArgs) {
        Cursor cursor = mDatabase.query(MeetingDbSchema.MeetingTable.NAME, null, whereClause, whereArgs,null,null,null);
        return new MeetingCursorWrapper(cursor);
    }

    private MeetingDetailCursorWrapper queryMeetingsDetail (String whereClause, String [] whereArgs) {
        Cursor cursor = mDetailDatabase.query(MeetingDetailDbSchema.MeetingDetailTable.NAME, null, whereClause, whereArgs,null,null,null);
        return new MeetingDetailCursorWrapper(cursor);
    }
}
