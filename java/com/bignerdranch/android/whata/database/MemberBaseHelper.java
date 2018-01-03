package com.bignerdranch.android.whata.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KYOME on 2018-01-03.
 */

public class MemberBaseHelper extends SQLiteOpenHelper {

    public final static int VERSION = 1;
    public final static String DATABASE_NAME = "memberBase.db";

    public MemberBaseHelper (Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ MemberDbSchema.MemberTable.NAME+ " ("
                + MemberDbSchema.MemberTable.Cols.MEMBERUUID + " PRIMARY KEY , "
                + MemberDbSchema.MemberTable.Cols.MEETINGUUID + " references "
                    + MeetingDbSchema.MeetingTable.NAME + " (" +MeetingDbSchema.MeetingTable.Cols.UUID+") on update cascade, "
                + MemberDbSchema.MemberTable.Cols.MEMBERNAME + " , "
                + MemberDbSchema.MemberTable.Cols.MEMBERCD + " , "
                + MemberDbSchema.MemberTable.Cols.MEMBERPHONE + " , "
                + MemberDbSchema.MemberTable.Cols.MEMBERBIRTHDAY +" , "
                + MemberDbSchema.MemberTable.Cols.MEMBERADDRESS +")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {

    }

}
