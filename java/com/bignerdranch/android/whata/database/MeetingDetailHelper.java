package com.bignerdranch.android.whata.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MeetingDetailHelper extends SQLiteOpenHelper {
    public static final int VERSION= 1;
    public static final String DATABASE_NAME = "meetingDetail.db";
    public MeetingDetailHelper (Context context){
        super(context,DATABASE_NAME,null,VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table "+MeetingDetailDbSchema.MeetingDetailTable.NAME+" ("
                +MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID + ", "
                +MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSCODE + ", "
                +MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSTIME
                + ", PRIMARY KEY (" +MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID
                + ", "+MeetingDetailDbSchema.MeetingDetailTable.Cols.DAYSCODE+")"
                + ", CONSTRAINT FK_MEETINGDETAIL FOREIGN KEY (" + MeetingDetailDbSchema.MeetingDetailTable.Cols.UUID+" )"
                + " REFERENCES "+ MeetingDbSchema.MeetingTable.NAME  +" (" +MeetingDbSchema.MeetingTable.Cols.UUID+") )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){

    }


}
