package com.bignerdranch.android.whata.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.bignerdranch.android.whata.Member;

import java.util.UUID;

/**
 * Created by KYOME on 2018-01-03.
 */

public class MemberCursorWrapper extends CursorWrapper {
    public MemberCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Member getMember() throws Exception {
        String memberUUID  = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERUUID));
        String meetingUUID  = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERUUID));
        String memberName  = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERNAME));
        String memberCd  = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERCD));
        String memberPhone  = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERPHONE));
        String memberBirthday  = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERBIRTHDAY));
        String memberAddress = getString(getColumnIndex(MemberDbSchema.MemberTable.Cols.MEMBERADDRESS));

        Member member = new Member(UUID.fromString(memberUUID));
        member.setMeetingId(UUID.fromString(meetingUUID));
        member.setName(memberName);
        member.setCd(memberCd);
        member.setPhone(memberPhone);
        member.setBirth(memberBirthday);
        member.setAddress(memberAddress);

        return member;
    }
}
