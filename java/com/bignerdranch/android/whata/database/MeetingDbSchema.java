package com.bignerdranch.android.whata.database;

/**
 * Created by KYOME on 2017-12-19.
 */
//스키마를 정의하는 클래스
    //구성요소 : 테이블명을 정의하는 내부 클래스 + 컬럼명을 정의하는 내부 클래스

public class MeetingDbSchema {
    public static final class MeetingTable{
        public static final String NAME = "meetings";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String LOCATION = "location";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";

        }
    }
}
