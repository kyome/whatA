package com.bignerdranch.android.whata;

import android.content.Context;

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

    private MemberManager (Context context) {
        mContext = context.getApplicationContext();
        mMembers = new ArrayList<>();

// 테스트용 참석자 100명
        for(int i = 0 ; i<100 ; i++) {
            Member member = new Member();
            member.setName("참석자 #"+i);
            mMembers.add(member);
        }

    }

    public static MemberManager get (Context context) {
        if(sMemberManager == null) {
            sMemberManager = new MemberManager(context);
        }
        return sMemberManager;
    }


    public List<Member> getMembers() {
        return mMembers;
    }

    public Member getMember(UUID id) {

        for(Member member : mMembers){
            if(member.getId().equals(id)){
                return member;
            }
        }
        return null;
    }

}
