package com.bignerdranch.android.whata;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class MeetingActivity extends SingleFragmentActivity implements MeetingListFragment.Callbacks {


    @Override
    protected Fragment createFragment() {
        return new MeetingListFragment();
    }

    @Override
    public void onMeetingSelected(Meeting meeting) {
       Intent intent = MemberListActivity.newIntent(this,meeting.getId());
        startActivity(intent);
    }
}
