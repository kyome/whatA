package com.bignerdranch.android.whata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KYOME on 2017-11-18.
 */

public class MeetingListFragment extends Fragment {

    private RecyclerView mMeetingRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        mMeetingRecyclerView = (RecyclerView)view.findViewById(R.id.meeting_recycler_view);
        mMeetingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private class  MeetingHolder extends RecyclerView.ViewHolder {
        public
    }

}

