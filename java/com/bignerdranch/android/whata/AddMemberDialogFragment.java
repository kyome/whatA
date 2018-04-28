package com.bignerdranch.android.whata;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by KYOME on 2018-02-12.
 */

public class AddMemberDialogFragment extends DialogFragment {

    public static final String EXTRA_ADDMEMBER = "addMember";

    private AddMemberDialogFragment mAddMemberDialogFragment;

    public static AddMemberDialogFragment newInstance(){
        AddMemberDialogFragment fragment = new AddMemberDialogFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_member,null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("구성원 추가").setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Member member = new Member();
                        member.setName("입력확인");
                        try {
                            member.setPhone("00000000");
                        member.setBirth("00000000");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        sendResult(Activity.RESULT_OK,member);
                    }
                }
        ).create();
    }


    private void sendResult(int resultCode, Member member) {
        if(getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ADDMEMBER,member);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
