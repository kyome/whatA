package com.bignerdranch.android.whata;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by KYOME on 2018-01-05.
 */

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;
    public static final String ARG_BUTTON_ID = "button_id";
    public static final String EXTRA_TIME = "time_picker_fragment";
    private int button_id;
    public static TimePickerFragment newInstance(int clickedButtonId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BUTTON_ID, clickedButtonId);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        button_id = getArguments().getInt(ARG_BUTTON_ID);
        Toast.makeText(getContext(),"받아온 버튼 ID"+Integer.toString(button_id),Toast.LENGTH_SHORT).show();
        Calendar mCalendar = Calendar.getInstance();

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int min = mCalendar.get(Calendar.MINUTE);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.timepicker_spinner,null);
        mTimePicker = (TimePicker)view.findViewById(R.id.timepicker_spinner);
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(min);

        return new AlertDialog.Builder(getActivity()).setView(view).setTitle("모임 시간 선택").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedHour = mTimePicker.getHour();
                int selectedMin = mTimePicker.getMinute();
                Date date = new GregorianCalendar(0,0,0,selectedHour,selectedMin).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        }).create();
    }

    private void sendResult(int resultCode, Date date) {
        if(getTargetFragment()==null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME,date);
        intent.putExtra(ARG_BUTTON_ID,button_id);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

}
