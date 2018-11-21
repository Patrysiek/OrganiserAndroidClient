package com.organiser.helpers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.organiser.R;
import com.organiser.acitvities.MainActivity;
import com.organiser.asyncTasks.asyncTasksCallbacks.ILoadTasksCallback;

import java.util.Calendar;

public class DateManager{

    private IsetDateText setDateTextListener;
    private ILoadTasksCallback loadTasksCallback;

    public DateManager(MainActivity listener){
        this.setDateTextListener = listener;
        this.loadTasksCallback = listener;
    }

    public void switchDay(int id,Calendar calendar) {
        switch (id) {
            case R.id.next_date_button:
                initDate(calendar, 1);
                break;
            case R.id.previous_date_button:
                initDate(calendar, -1);
                break;
        }
    }
    public void onDateTextClick(Calendar calendar, Context context) {
        new DatePickerDialog(context, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, (DatePicker datePicker, int year, int month, int dayOfMonth)-> {
            calendar.set(year,month,dayOfMonth);
            initDate(calendar,0);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void initDate(Calendar calendar, int day) {
        calendar.add(Calendar.DAY_OF_YEAR, day);

        setDateTextListener.setDateText( ObjectParser.parserDateToString(calendar.getTime()));
        loadTasksCallback.loadTasksAfterChange();
    }
}
