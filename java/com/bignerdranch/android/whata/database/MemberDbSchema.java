package com.bignerdranch.android.whata.database;

/**
 * Created by KYOME on 2018-01-02.
 */

public class MemberDbSchema {
    public static final class MemberTable {

    public static final String name = "members";

        public static final class Cols {
            public static final String MEMBERUUID = "memberId";
            public static final String MEETINGUUID = "uuid"; //FORIEGN KEY : MEETING_ID
            public static final String MEMBERNAME = "memberName" ;
            public static final String MEMBERCD = "memberCd";
            public static final String MEMBERPHONE = "memberPhone";
            public static final String MEMBERBIRTHDAY = "memberBirthday";

        }

    }
}
