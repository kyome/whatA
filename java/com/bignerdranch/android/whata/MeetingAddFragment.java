package com.bignerdranch.android.whata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by KYOME on 2017-12-26.
 */

public class MeetingAddFragment extends Fragment {
    private static final String ARG_MEETING_ID = "meeting_id";
    private Meeting mMeeting;
    private EditText mTitleEditText;
    private EditText mLocationEditText;
    private EditText mDescriptionEditText;

    private Button [] mAddMeetingDayButtons = new Button[7];


    private TextView mTextView;
    private ImageView mAddMeetingDetailTimeButton;
    private LinearLayout mAddMeetingTime;

    private LinearLayout [] mAddMeetingTimes = new LinearLayout[7];

    boolean checkDetail;



    public static MeetingAddFragment newInstance() {

        Bundle args = new Bundle();

        MeetingAddFragment fragment = new MeetingAddFragment();
        args.putSerializable(ARG_MEETING_ID,UUID.randomUUID());
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID meetingID = (UUID)getArguments().getSerializable(ARG_MEETING_ID);
//        mMeeting = MeetingManager.get(getActivity()).getMeeting(meetingID);
        mMeeting = new Meeting(meetingID);
        checkDetail = false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_add_meeting,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menue_item_ok) {
            if(mMeeting.getTitle()==null){
            Toast.makeText(this.getContext(),"모임명은 필수 입력입니다.",Toast.LENGTH_SHORT).show();
            } else {
                MeetingManager.get(this.getContext()).addMeeting(mMeeting);
//                Toast.makeText(this.getContext(),mMeeting.getTitle() +","+mMeeting.getLocation()  +","+ mMeeting.getDescription(),Toast.LENGTH_SHORT).show();
                Intent intent = MeetingListActivity.newIntent(this.getContext());
                startActivity(intent);
            }
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meeting,container,false);


        //모임명
        mTitleEditText = view.findViewById(R.id.add_meeting_title);
        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mMeeting.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLocationEditText = view.findViewById(R.id.add_meeting_location);
        mLocationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMeeting.setLocation(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDescriptionEditText = view.findViewById(R.id.add_meeting_description);
        mDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMeeting.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        for(int i = 0 ; i <mAddMeetingDayButtons.length; i++ ) {
            mAddMeetingDayButtons[i] = view.findViewById(getResources().getIdentifier("com.bignerdranch.android.whata:id/add_meeting_day_button"+i,null,null));
            mAddMeetingDayButtons[i].setSelected(false);

        }


        mAddMeetingDayButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[0].setSelected(! mAddMeetingDayButtons[0].isSelected());

                if(mAddMeetingDayButtons[0].isSelected()==false){
                    mAddMeetingTimes[0].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[0].setVisibility(View.VISIBLE);
                }

            }
        });

        mAddMeetingDayButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[1].setSelected(! mAddMeetingDayButtons[1].isSelected());

                if(mAddMeetingDayButtons[1].isSelected()==false){
                    mAddMeetingTimes[1].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[1].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[2].setSelected(! mAddMeetingDayButtons[2].isSelected());

                if(mAddMeetingDayButtons[2].isSelected()==false){
                    mAddMeetingTimes[2].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[2].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[3].setSelected(! mAddMeetingDayButtons[3].isSelected());

                if(mAddMeetingDayButtons[3].isSelected()==false){
                    mAddMeetingTimes[3].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[3].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[4].setSelected(! mAddMeetingDayButtons[4].isSelected());

                if(mAddMeetingDayButtons[4].isSelected()==false){
                    mAddMeetingTimes[4].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[4].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[5].setSelected(! mAddMeetingDayButtons[5].isSelected());

                if(mAddMeetingDayButtons[5].isSelected()==false){
                    mAddMeetingTimes[5].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[5].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[6].setSelected(! mAddMeetingDayButtons[6].isSelected());

                if(mAddMeetingDayButtons[6].isSelected()==false){
                    mAddMeetingTimes[6].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[6].setVisibility(View.VISIBLE);
                }
            }
        });


//        layout.setVisibility(View.VISIBLE);
//        해당 뷰를 보여줌
//
//        layout.setVisibility(View.INVISIBLE);
//        해당 뷰를 안 보여줌(공간은 존재)
//
//        layout.setVisibility(View.GONE);
//        해당 뷰를 안 보여줌(공간마저 감춤)


        mAddMeetingDetailTimeButton = view.findViewById(R.id.add_meeting_detail_time_button);
        mAddMeetingDetailTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDetail = !checkDetail;
                if(checkDetail==false){
                    mAddMeetingTime.setVisibility(View.GONE);
                } else {
                    mAddMeetingTime.setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingTime = view.findViewById(R.id.add_meeting_time_days);
        mAddMeetingTime.setVisibility(View.GONE);


        for(int i = 0; i < mAddMeetingTimes.length; i++){
            mAddMeetingTimes[i] = view.findViewById(getResources().getIdentifier("com.bignerdranch.android.whata:id/add_meeting_time_day"+i,null,null));
            if(!mAddMeetingDayButtons[i].isSelected()){
                mAddMeetingTimes[i].setVisibility(View.GONE);
            }
        }


        return view;

    }

}
