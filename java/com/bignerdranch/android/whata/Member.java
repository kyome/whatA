package com.bignerdranch.android.whata;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KYOME on 2017-12-15.
 * 참여멤버 모델을 정의
 */
// 정규식 숫자만 Pattern.compile("(^[0-9]*$)");

public class Member {
    private UUID mId;
    private UUID mMeetingId;
    private String mName;
    private String mCd;
    private String mPhone;
    private String mBirth;
    private String mAddress;
    private Pattern mPatternOnlyNum = Pattern.compile("(^[0-9]*$)");

    public Member() {
        this(UUID.randomUUID());
    }

    public Member(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public UUID getMeetingId() {
        return mMeetingId;
    }

    public void setMeetingId(UUID meetingId) {
        mMeetingId = meetingId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {

        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) throws Exception {
        Matcher matcher = mPatternOnlyNum.matcher(phone);
        if(matcher.find()){
            mPhone = phone;
        }else {
            throw new Exception("숫자만 입력가능");
        }
    }

    public String getCd() {
        return mCd;
    }

    public void setCd(String cd) {
        mCd = cd;
    }

    public String getBirth() {
        return mBirth;
    }

    public void setBirth(String birth) throws Exception {

        Matcher matcher = mPatternOnlyNum.matcher(birth);
        if(matcher.find()){
            mBirth = birth;
        }else {
            throw new Exception("숫자만 입력가능");
        }
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }


}
