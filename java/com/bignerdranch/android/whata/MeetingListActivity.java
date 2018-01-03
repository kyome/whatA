package com.bignerdranch.android.whata;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class MeetingListActivity extends SingleFragmentActivity implements MeetingListFragment.Callbacks {


    @Override
    protected Fragment createFragment() {
        return new MeetingListFragment();
    }

    @Override
    public void onMeetingSelected(Meeting meeting) {
       Intent intent = MemberListActivity.newIntent(this,meeting.getId());
        startActivity(intent);
    }

    @Override
    public void onAddSelected(){
        Intent intent = MeetingAddActivity.newIntent(this);
        startActivity(intent);
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext,MeetingListActivity.class);
        return intent;
    }
}
