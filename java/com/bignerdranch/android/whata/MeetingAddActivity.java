package com.bignerdranch.android.whata;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public class MeetingAddActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MeetingAddActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment(){
        Fragment fragment = MeetingAddFragment.newInstance();
        return fragment;
    }

}
