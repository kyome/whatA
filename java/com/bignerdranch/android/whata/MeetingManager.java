package com.bignerdranch.android.whata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.whata.database.MeetingBaseHelper;
import com.bignerdranch.android.whata.database.MeetingCursorWrapper;
import com.bignerdranch.android.whata.database.MeetingDbSchema;

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
    public static MeetingManager get(Context context) {
        if (sMeetingManager == null) {
            sMeetingManager = new MeetingManager(context);
        }
        return sMeetingManager;
    }

    private MeetingManager(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new MeetingBaseHelper(mContext).getWritableDatabase();
    }

    public void addMeeting(Meeting meeting) {
        ContentValues values = getContentValues(meeting);
        mDatabase.insert(MeetingDbSchema.MeetingTable.NAME,null,values);
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

    public void updateMeeting(Meeting meeting) throws Exception {
            String uuidString = meeting.getId().toString();
            ContentValues values = getContentValues(meeting);

            mDatabase.update(MeetingDbSchema.MeetingTable.NAME, values, MeetingDbSchema.MeetingTable.Cols.UUID + "= ?", new String[] { uuidString});
    }


    private static ContentValues getContentValues( Meeting meeting) {
        ContentValues values = new ContentValues();
        values.put(MeetingDbSchema.MeetingTable.Cols.UUID, meeting.getId().toString());
        values.put(MeetingDbSchema.MeetingTable.Cols.TITLE,meeting.getTitle());
        values.put(MeetingDbSchema.MeetingTable.Cols.DAYS, meeting.getDays().toString());
        values.put(MeetingDbSchema.MeetingTable.Cols.LOCATION, meeting.getLocation());
        values.put(MeetingDbSchema.MeetingTable.Cols.DESCRIPTION, meeting.getDescription());

        return values;
    }

    private MeetingCursorWrapper queryMeetings(String whereClause, String [] whereArgs) {
        Cursor cursor = mDatabase.query(MeetingDbSchema.MeetingTable.NAME, null, whereClause, whereArgs,null,null,null);
        return new MeetingCursorWrapper(cursor);
    }

}
