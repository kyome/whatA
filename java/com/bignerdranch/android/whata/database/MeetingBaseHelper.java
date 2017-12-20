package com.bignerdranch.android.whata.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KYOME on 2017-12-19.
 */

public class MeetingBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DTATBASE_NAME = "meetingBase.db";

    public MeetingBaseHelper(Context context) {
        super(context, DTATBASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
