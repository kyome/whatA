package com.bignerdranch.android.whata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by KYOME on 2017-12-15.
 */

public class MemberListFragment extends Fragment {
    private final String TAG = "MemberListFragment";
    private static final String ARG_MEETING_ID = "meeting_id";

    private Meeting mMeeting;
    private RecyclerView mMemberRecyclerView;

    private MemberAdapter mAdapter;

    public static MemberListFragment newInstance(UUID meetingId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEETING_ID,meetingId);

        MemberListFragment fragment = new MemberListFragment();
        fragment.setArguments(args);
        return  fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID meetingId = (UUID) getArguments().getSerializable(ARG_MEETING_ID);
        mMeeting = MeetingManager.get(getActivity()).getMeeting(meetingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_list,container,false);
        mMemberRecyclerView = (RecyclerView)v.findViewById(R.id.member_recycler_view);
        mMemberRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }


    private  void updateUI() {
        MemberManager memberManger = MemberManager.get(getActivity());
        List<Member> members = memberManger.getMembers();


        mAdapter = new MemberAdapter(members);
//        Log.d(TAG,mMeetingRecyclerView.toString());
        mMemberRecyclerView.setAdapter(mAdapter);
    }

    private class MemberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mName;
        public TextView mBirth;

        private Member mMember;

        public MemberHolder (View viewItem) {
            super(viewItem);
            viewItem.setOnClickListener(this);
            mName = (TextView)viewItem.findViewById(R.id.member_name);
            mBirth = (TextView)viewItem.findViewById(R.id.member_birth);
        }

        public void bindMember(Member member){
            mMember = member;
            mName.setText(member.getName());
            mBirth.setText(member.getPhone());

        }
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),mMeeting.getTitle()+"의"+mMember.getName() + "선택!", Toast.LENGTH_SHORT).show();
        }
    }

    private class MemberAdapter extends RecyclerView.Adapter<MemberHolder> {
        public List<Member> mMembers;

        public MemberAdapter(List<Member> members) {
            mMembers = members;
        }

        @Override
        public MemberHolder onCreateViewHolder (ViewGroup parent, int viewType ){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.member_list_item,parent,false);

            return new MemberHolder(view);
        }

        @Override
        public void onBindViewHolder(MemberHolder holder, int position) {
            Member member = mMembers.get(position);
            holder.bindMember(member);
        }

        @Override
        public int getItemCount() {
            return mMembers.size();
        }
    }
}
