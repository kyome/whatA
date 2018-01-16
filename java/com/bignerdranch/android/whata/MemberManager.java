package com.bignerdranch.android.whata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.bignerdranch.android.whata.database.MemberBaseHelper;
import com.bignerdranch.android.whata.database.MemberCursorWrapper;
import com.bignerdranch.android.whata.database.MemberDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by KYOME on 2017-12-15.
 */

public class MemberManager {
    static MemberManager sMemberManager;
    private Context mContext;
    private List<Member> mMembers;
    private SQLiteDatabase mDatabase;

    private MemberManager (Context context) {
        mContext = context.getApplicationContext();

        mDatabase =  new MemberBaseHelper(mContext).getWritableDatabase();

        mMembers = new ArrayList<>();

// 테스트용 참석자 100명
        for(int i = 0 ; i<10 ; i++) {
            Member member = new Member();
            member.setName("참석자 #"+i);

            //MeetingId 임시로 첫번째 것을 갖고옴 -> 테스트 데이터를 확인하기 위해
            member.setMeetingId((UUID)MeetingManager.get(context).getMeetings().get(0).getId());
            member.setCd("");

            member.setAddress("");
            try {
                member.setBirth("9999123");
                member.setPhone("123123");
            } catch (Exception e) {
                e.getStackTrace();
            }

            mMembers.add(member);
            addMember(member);
        }

    }

    public void addMember(Member member) {
        ContentValues values = getContentValues(member);
        mDatabase.insert(MemberDbSchema.MemberTable.NAME,null,values);
    }


    public static MemberManager get (Context context) {
        if(sMemberManager == null) {
            sMemberManager = new MemberManager(context);
        }
        return sMemberManager;
    }


    public List<Member> getMembers() {
        ArrayList<Member> mMembersNew = new ArrayList<>();

        MemberCursorWrapper cursor = queryMembers(null,null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                mMembersNew.add(cursor.getMember());
                cursor.moveToNext();
            }
        } catch (Exception e)
        {
            e.getStackTrace();
        }
        finally {
        cursor.close();
        }

        return //mMembers
                mMembersNew;
    }

    public Member getMember(UUID id) {

        for(Member member : mMembers){
            if(member.getId().equals(id)){
                return member;
            }
        }
        return null;
    }


    private static ContentValues getContentValues(Member member) {
        ContentValues values = new ContentValues();
        values.put(MemberDbSchema.MemberTable.Cols.MEMBERUUID, member.getId().toString());
        values.put(MemberDbSchema.MemberTable.Cols.MEETINGUUID, member.getMeetingId().toString());
        values.put(MemberDbSchema.MemberTable.Cols.MEMBERNAME, member.getName().toString());
        values.put(MemberDbSchema.MemberTable.Cols.MEMBERCD, member.getCd().toString());
        values.put(MemberDbSchema.MemberTable.Cols.MEMBERPHONE, member.getPhone().toString());
        values.put(MemberDbSchema.MemberTable.Cols.MEMBERBIRTHDAY, member.getBirth().toString());
        values.put(MemberDbSchema.MemberTable.Cols.MEMBERADDRESS, member.getAddress().toString());

        return values;
    }

    private MemberCursorWrapper queryMembers(String whereClause, String [] whereArgs) {
        Cursor cursor = mDatabase.query(MemberDbSchema.MemberTable.NAME, null, whereClause, whereArgs,null,null,null);
        return new MemberCursorWrapper(cursor);
    }

}
