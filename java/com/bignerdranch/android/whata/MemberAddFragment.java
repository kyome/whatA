package com.bignerdranch.android.whata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by KYOME on 2018-01-21.
 */

public class MemberAddFragment extends Fragment {
    private static final String ARG_MEETING_ID = "meeting_id";
    private UUID meetingId;
    private EditText mAddMemberName;
    private EditText mAddMemberPhone;
    private EditText mAddMemberBirth;
    private Member mMember;


    public static MemberAddFragment newInstance(UUID meetingId) {
        Bundle args= new Bundle();
        args.putSerializable(ARG_MEETING_ID,meetingId);
        MemberAddFragment fragment = new MemberAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        meetingId = (UUID) getArguments().getSerializable(ARG_MEETING_ID);
        mMember = new Member(UUID.randomUUID());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_add_member,container,false);

        mAddMemberName = view.findViewById(R.id.add_member_name);

        mAddMemberName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mMember.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAddMemberPhone = view.findViewById(R.id.add_member_phone);

        mAddMemberPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                try{
                mMember.setPhone((String)charSequence);
                } catch (Exception e) {
                    charSequence = "";
                    Toast.makeText(getContext(),"숫자만 입력가능합니다.", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAddMemberBirth = view.findViewById(R.id.add_member_birth);

        mAddMemberBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                try{
                    mMember.setBirth((String)charSequence);
                } catch (Exception e) {
                    charSequence = "";
                    Toast.makeText(getContext(),"숫자만 입력가능합니다.", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

}
