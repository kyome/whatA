package com.bignerdranch.android.whata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by KYOME on 2017-12-26.
 */

public class MeetingAddFragment extends Fragment {
    private static final String ARG_MEETING_ID = "meeting_id";
    private static final int REQUEST_TIME = 0;

    private Meeting mMeeting;
    private EditText mTitleEditText;
    private EditText mLocationEditText;
    private EditText mDescriptionEditText;
    private Button mAddMemberButtonEach;

    private RecyclerView mAddMemberRecyclerView;
    private ArrayList<Member> tempMemberList = new ArrayList<>();

    private LinearLayout[] mAddMeetingTimes = new LinearLayout[7];
    private Button[] mAddMeetingDayButtons = new Button[7];
    private TextView[] mAddMeetingTimeTextViews = new TextView[7];
    private TextView mAddMeetingAllTimeTextView;

    private ImageView mAddMeetingDetailTimeButton;
    private LinearLayout mAddMeetingTime;
    boolean checkDetail;
    private String[] backupDaysTime;
    private String backupTitle;
    private String backupLocation;
    private String backupDescription;
    private int button_id;
    private Date date;

    public MeetingAddFragment() {
        super();

    }

    // 프래그먼트 인텐트 객체 생성 (static으로 외부에서 객체를 받을 수 있는 패턴)
    public static MeetingAddFragment newInstance() {

        Bundle args = new Bundle();

        MeetingAddFragment fragment = new MeetingAddFragment();
        args.putSerializable(ARG_MEETING_ID, UUID.randomUUID());
        fragment.setArguments(args);


        return fragment;
    }
//
//    public String[] backupBeforeAdd() {
//        backupDaysTime = new String[8];
//
//        backupTitle = mMeeting.getTitle();
//        backupLocation = mMeeting.getLocation();
//        backupDescription = mMeeting.getDescription();
//
////백업 작성중 시간 설정하는 것한 후에 시간에 대한 백업 추가할 것
////백업은 각 요일만 백업, 전체에대한 시간은 각요일별 시간이 모두 시간이 같거나 null이면 세팅되도록 로직 추가
////전체시간 onClickListener안에서 만약에 내용이 바뀌면 days 배열의내용을 모두 전체시간으로 변경하라는 내용 추가
//
//
//    }

    //프레그먼트 데이터 백업을 위한 변수 저장(미완성) checkDetail 도 추가 필요
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if (backupDaysTime != null) {
            savedInstanceState.putStringArray("backupDaysTime", backupDaysTime);
            savedInstanceState.putString("backupTitle", backupTitle);
            savedInstanceState.putString("backupLocation", backupLocation);
            savedInstanceState.putString("backupDescription", backupDescription);
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //액티비티에서 프레그먼트를 갱신 인텐트에 모임ID를 생성 -> 인텐트에 실어 놓은 모임ID(meeting Id)를 받음, 데이터 추가를 위해 mMeeting 에 작성
        UUID meetingID = (UUID) getArguments().getSerializable(ARG_MEETING_ID);
        mMeeting = new Meeting(meetingID);
        checkDetail = false;


        //멤버추가임시 리스트 테스트
        for (int i = 0; i < 100; i++) {

            Member member = new Member();
            member.setMeetingId((UUID) getArguments().getSerializable(ARG_MEETING_ID));
            try {
                member.setBirth("000000");
                member.setPhone("000000");
            } catch (Exception e) {
                e.printStackTrace();
            }

            member.setName("Test" + i);
            member.setCd("01");
            tempMemberList.add(member);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_TIME) {
            date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            Toast.makeText(getContext(), date.toString(), Toast.LENGTH_SHORT).show();
        }
        button_id = (int) data.getSerializableExtra(TimePickerFragment.ARG_BUTTON_ID);

        if(button_id == R.id.add_meeting_timeAll){
            mAddMeetingAllTimeTextView.setText(DateFormat.format ("AA hh:mm",date).toString());

            for(int i = 0 ; i < mAddMeetingTimeTextViews.length ; i++) {
                    mAddMeetingTimeTextViews[i].setText(DateFormat.format("AA hh:mm", date).toString());
            }

        }
        for(int i = 0 ; i < mAddMeetingTimeTextViews.length ; i++) {
            if (button_id == getResources().getIdentifier("com.bignerdranch.android.whata:id/add_meeting_time" + i, null, null)) {
                mAddMeetingTimeTextViews[i].setText(DateFormat.format("AA hh:mm", date).toString());
            }
        }
    }



    //메뉴(완료버튼) 뷰로 생성
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_ok, menu);
    }

    //메뉴(완료버튼) 코드 , 필수 입력 정보가 Null 일경우 저장되지 않도록 함
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menue_item_ok) {
            if (mMeeting.getTitle() == null) {
                Toast.makeText(this.getContext(), "모임명은 필수 입력입니다.", Toast.LENGTH_SHORT).show();
            } else {
                MeetingManager.get(this.getContext()).addMeeting(mMeeting);
                Intent intent = MeetingListActivity.newIntent(this.getContext());
                startActivity(intent);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);


        //모임명 EditText View
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

        //장소 EditText View
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

        //설명 EditText View
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


        //TimePicker 다이얼로그로 연결하는 onClickListener 생성
        //TimePickerFragment에 클릭한 버튼의 ID를 전송하고 인텐트를 반환받음
        //setTargetFragment에서 프래그먼트에서 돌아올 프레그먼트, 상수 설정
        //프래그먼트 매니저를 인자로 프래그먼트 보여줌
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), Integer.toString(view.getId()), Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(view.getId());
                dialog.setTargetFragment(MeetingAddFragment.this, REQUEST_TIME);
                dialog.show(manager, "DIALOG_TIME");
            }
        };

        //전체 시간을 보여주는 Text View 생성, 리스너 추가
        mAddMeetingAllTimeTextView = view.findViewById(R.id.add_meeting_timeAll);
        mAddMeetingAllTimeTextView.setOnClickListener(onClickListener);



        // 상세 시간 묶음(LinearLayout)의 뷰 연결, 기본값을 안보이도록 설정 -> mAddMeetingDetailTimeButton 로 보여줄지 여부 설정
        mAddMeetingTime = view.findViewById(R.id.add_meeting_time_days);
        mAddMeetingTime.setVisibility(View.GONE);

//        layout.setVisibility(View.VISIBLE);
//        해당 뷰를 보여줌
//
//        layout.setVisibility(View.INVISIBLE);
//        해당 뷰를 안 보여줌(공간은 존재)
//
//        layout.setVisibility(View.GONE);
//        해당 뷰를 안 보여줌(공간마저 감춤)

        //상세 시간 설정을 위해 요일별 정보를 보여줄지 여부 선택하는 버튼 생성, 리스너 추가
        mAddMeetingDetailTimeButton = view.findViewById(R.id.add_meeting_detail_time_button);
        mAddMeetingDetailTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDetail = !checkDetail;
                if (checkDetail == false) {
                    mAddMeetingTime.setVisibility(View.GONE);
                } else {
                    mAddMeetingTime.setVisibility(View.VISIBLE);
                }
            }
        });



        //요일별(일~토) 시간을 보여주는 Text View 생성, 리스너 추가
        for (int i = 0; i < mAddMeetingTimeTextViews.length; i++) {
            mAddMeetingTimeTextViews[i] = view.findViewById(getResources().getIdentifier("com.bignerdranch.android.whata:id/add_meeting_time" + i, null, null));
            mAddMeetingTimeTextViews[i].setOnClickListener(onClickListener);
        }

        //요일별(일~토) 선택 Button View 생성, 미선택을 기본값으로 설정
        for (int i = 0; i < mAddMeetingDayButtons.length; i++) {
            mAddMeetingDayButtons[i] = view.findViewById(getResources().getIdentifier("com.bignerdranch.android.whata:id/add_meeting_day_button" + i, null, null));
            mAddMeetingDayButtons[i].setSelected(false);

        }

        //요일 선택 Button별 리스너 설정 (Inner Class라서 i를 통한 반복 설정 불가)
        //기본 선택값을 안보이는 것으로 설정, 선택시 보이는 것으로 변경
        mAddMeetingDayButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[0].setSelected(!mAddMeetingDayButtons[0].isSelected());

                if (mAddMeetingDayButtons[0].isSelected() == false) {
                    mAddMeetingTimes[0].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[0].setVisibility(View.VISIBLE);
                }

            }
        });

        mAddMeetingDayButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[1].setSelected(!mAddMeetingDayButtons[1].isSelected());

                if (mAddMeetingDayButtons[1].isSelected() == false) {
                    mAddMeetingTimes[1].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[1].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[2].setSelected(!mAddMeetingDayButtons[2].isSelected());

                if (mAddMeetingDayButtons[2].isSelected() == false) {
                    mAddMeetingTimes[2].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[2].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[3].setSelected(!mAddMeetingDayButtons[3].isSelected());

                if (mAddMeetingDayButtons[3].isSelected() == false) {
                    mAddMeetingTimes[3].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[3].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[4].setSelected(!mAddMeetingDayButtons[4].isSelected());

                if (mAddMeetingDayButtons[4].isSelected() == false) {
                    mAddMeetingTimes[4].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[4].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[5].setSelected(!mAddMeetingDayButtons[5].isSelected());

                if (mAddMeetingDayButtons[5].isSelected() == false) {
                    mAddMeetingTimes[5].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[5].setVisibility(View.VISIBLE);
                }
            }
        });

        mAddMeetingDayButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingDayButtons[6].setSelected(!mAddMeetingDayButtons[6].isSelected());

                if (mAddMeetingDayButtons[6].isSelected() == false) {
                    mAddMeetingTimes[6].setVisibility(View.GONE);
                } else {
                    mAddMeetingTimes[6].setVisibility(View.VISIBLE);
                }
            }
        });






        for (int i = 0; i < mAddMeetingTimes.length; i++) {
            mAddMeetingTimes[i] = view.findViewById(getResources().getIdentifier("com.bignerdranch.android.whata:id/add_meeting_time_day" + i, null, null));
            if (!mAddMeetingDayButtons[i].isSelected()) {
                mAddMeetingTimes[i].setVisibility(View.GONE);
            }
        }

        mAddMemberButtonEach = view.findViewById(R.id.add_member_button_each);


        //추가한 구성원을 보여주기위한 RecyclerView 생성 ( 4열 그리드 레이아웃)
        mAddMemberRecyclerView = view.findViewById(R.id.add_member_recycler_view);
        mAddMemberRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mAddMemberRecyclerView.setAdapter(new TempMemberAdapter());


        return view;

    }


    private class TempMemberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout addedmember;
        TextView addedMemberName;
        ImageButton addedMemberDelete;
        Member mMember;

        public TempMemberHolder(View viewItem) {
            super(viewItem);
            viewItem.setOnClickListener(this);
            addedmember = viewItem.findViewById(R.id.added_member);
            addedMemberName = viewItem.findViewById(R.id.added_member_name);
            addedMemberDelete = viewItem.findViewById(R.id.added_member_delete);
            addedMemberDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), mMember.getName() + " 삭제하자아아", Toast.LENGTH_SHORT).show();
                }
            });
        }


        public void bindView(Member member) {
            mMember = member;
            addedMemberName.setText(mMember.getName());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), mMember.getName() + " 수정 하자아아", Toast.LENGTH_SHORT).show();
        }
    }

    private class TempMemberAdapter extends RecyclerView.Adapter<TempMemberHolder> {
        @Override
        public TempMemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View item = inflater.inflate(R.layout.member_add_grid_item, parent, false);
            return new TempMemberHolder(item);
        }

        @Override
        public void onBindViewHolder(TempMemberHolder tempMemberHolder, int position) {
            tempMemberHolder.bindView(tempMemberList.get(position));
        }

        @Override
        public int getItemCount() {
            return tempMemberList.size();
        }
    }

}
