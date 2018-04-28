package com.bignerdranch.android.whata;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by KYOME on 2018-01-21.
 */

public class MemberAddActivity extends SingleFragmentActivity {
    private static final String EXTRA_MEETING_ID = "meeting_id";

    public static Intent newIntent( Context packageContext, UUID meetingId) {
            Intent intent = new Intent(packageContext, MemberAddActivity.class);
            intent.putExtra(EXTRA_MEETING_ID,meetingId);
            return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID meetingId= (UUID) getIntent().getSerializableExtra(EXTRA_MEETING_ID);

        return MemberAddFragment.newInstance(meetingId);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_meeting;
    }

}
