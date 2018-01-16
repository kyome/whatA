package com.bignerdranch.android.whata;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


public class MeetingListFragment extends Fragment {

    private final String TAG = "TAG";
    private RecyclerView mMeetingRecyclerView;
    private Callbacks mCallbacks;

    private MeetingAdapter mAdapter;

    public interface Callbacks {
        void onMeetingSelected (Meeting meeting);
        void onAddSelected();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
        mMeetingRecyclerView =null;
    }

    @Override
    public void onCreate(Bundle saveInstanceStatue) {
        super.onCreate(saveInstanceStatue);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final int rId = R.id.menu_item_new_meeting;
        int  itemId = item.getItemId();

        Log.d(TAG, String.valueOf(rId == itemId));
        Toast.makeText(this.getContext(), String.valueOf(itemId==rId), Toast.LENGTH_LONG).show();

        if(itemId==rId){
//            Toast.makeText(this.getContext(), "같음", Toast.LENGTH_LONG).show();
            mCallbacks.onAddSelected();
            return true;

        } else {
//            Toast.makeText(this.getContext(), "서로같지않음", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
//        Meeting meeting = new Meeting();
//        Fragment fragment = MeetingAddFragment.newInstance();
//        mCallbacks.onAddSelected();


//        if (itemId==rId){
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
//        switch (itemId) {
//            case  rId :
//                return true;
////                Meeting meeting = new Meeting();
////
////                Fragment fragment = MeetingAddFragment.newInstance(meeting.getId());
////                mCallbacks.onAddSelected(meeting);
////
//////              돌아올때 result 값을 받아서 성공하면 addMeeting(meeting) 수행하도록
////
////                MeetingManager.get(getActivity()).addMeeting(meeting);
////                updateUI();
//
//
//                default:
//                    return super.onOptionsItemSelected(item);
//        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        mMeetingRecyclerView = (RecyclerView)view.findViewById(R.id.meeting_recycler_view);
        mMeetingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private  void updateUI() {
        MeetingManager meetingManager = MeetingManager.get(getActivity());
        List<Meeting> meetings = meetingManager.getMeetings();

        if(mAdapter==null){
            mAdapter = new MeetingAdapter(meetings);
//        Log.d(TAG,mMeetingRecyclerView.toString());
            mMeetingRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setMeetings(meetings);
            mAdapter.notifyDataSetChanged();
        }
    }
    private class  MeetingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        public TextView mSubTitleTextView;
        public ImageView mTimeImageView;



        private Meeting mMeeting;

        public MeetingHolder(View viewItem) {
            super(viewItem);
            viewItem.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.meeting_title);
            mSubTitleTextView = (TextView) itemView.findViewById(R.id.meeting_subtitle);
            mTimeImageView = (ImageView) itemView.findViewById(R.id.meeting_time);

        }

        public void bindMeeting (Meeting meeting) {
            mMeeting =  meeting;
            mTitleTextView.setText(mMeeting.getTitle());
            mSubTitleTextView.setText(mMeeting.getDays().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),mMeeting.getTitle() + "선택!", Toast.LENGTH_SHORT).show();
            mCallbacks.onMeetingSelected(mMeeting);
        }
    }

    private class MeetingAdapter extends RecyclerView.Adapter<MeetingHolder> {
        private List<Meeting> mMeetings;

        public MeetingAdapter(List<Meeting> meetings) {
            mMeetings = meetings;
        }
        @Override
        public MeetingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.meeting_list_item,parent,false);
            return new MeetingHolder(view);
        }

        @Override
        public void onBindViewHolder(MeetingHolder holder, int position) {
            Meeting meeting = mMeetings.get(position);
            holder.bindMeeting(meeting);
        }

        @Override
        public int getItemCount() {
            return mMeetings.size();
        }

        public void setMeetings (List<Meeting> meetings) {
            mMeetings = meetings;
        }

    }
}

