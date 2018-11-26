package com.organiser.helpers;

import com.organiser.asyncTasks.asyncTasksCallbacks.ILoadTasksCallback;

import java.util.Calendar;

public class DateInitializer {

    private IsetDateText setDateTextListener;
    private ILoadTasksCallback loadTasksCallback;

    public DateInitializer(ILoadTasksCallback tasksCallback, IsetDateText setDateTextListener){
        this.setDateTextListener = setDateTextListener;
        this.loadTasksCallback = tasksCallback;
    }

    public void initDate(Calendar calendar, int day) {
        calendar.add(Calendar.DAY_OF_YEAR, day);

        setDateTextListener.setDateText( ObjectParser.parserDateToString(calendar.getTime()));
        loadTasksCallback.loadTasksAfterChange();
    }
}
