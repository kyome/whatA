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
    private String mName;
    private String mTel;
    private String mBirth;
    private String mAddress;
    private Pattern mPatternOnlyNum = Pattern.compile("(^[0-9]*$)");

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {

        mName = name;
    }

    public String getTel() {
        return mTel;
    }

    public void setTel(String tel) throws Exception {
        Matcher matcher = mPatternOnlyNum.matcher(tel);
        if(matcher.find()){
            mTel = tel;
        }else {
            throw new Exception("숫자만 입력가능");
        }
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
