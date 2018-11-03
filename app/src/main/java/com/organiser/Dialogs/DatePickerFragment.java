package com.organiser.Dialogs;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import com.organiser.R;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    DatePickerListener mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try {
            mListener = (DatePickerListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @RequiresApi(api = 28)
    @SuppressLint("ResourceAsColor")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it

        //Theme_DeviceDefault_Dialog_MinWidth
        DatePickerDialog dialog =  new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, this, year, month, day);
      dialog.getWindow().getDecorView().setBackgroundColor(R.color.basicOrganiserBgColor);
      dialog.getWindow().getDecorView().setAlpha(132);
        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);

        mListener.dateChangerFromPicker(calendar.getTime());
        //Toast.makeText(getContext(),ObjectParser.parserDateToString(calendar.getTime()),Toast.LENGTH_SHORT).show();
    }


    public interface DatePickerListener {
        void dateChangerFromPicker(Date date);
    }

}