package com.organiser.acitvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import com.organiser.R;
import com.organiser.configuration.ActivityConfig;
import com.organiser.services.TaskService;
import com.organiser.somePackage.ObjectParser;
import com.organiser.task.Task;
import com.organiser.task.TaskBar;
import com.organiser.task.TaskDAO;
import com.organiser.user.User;
import com.organiser.user.UserDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dateText,userNameTitle;
    private Button previousDateButton,nextDateButton;
    private LinearLayout linearLayout;
    private ArrayList<TaskBar> myTasks;
    private Calendar calendar;
    private User user;
    private TaskService taskService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfig.setFullScreen(this);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.list_of_tasks);
        initUser();
        initUserNameTitle();
        taskService = new TaskService("tabela");
        calendar = Calendar.getInstance();
        initDateText();
        myTasks = new ArrayList<>();
        try {
            List<Task> list = taskService.getAllTasksFromDay("26/04/1997");

            for(Task t : list){
                myTasks.add(new TaskBar(t.getDescription(),this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(TaskBar tb : myTasks)
        linearLayout.addView(tb.getTextView());


        previousDateButton = findViewById(R.id.previous_date_button);
        nextDateButton = findViewById(R.id.next_date_button);
    }

    private void initUserNameTitle() {
        userNameTitle = findViewById(R.id.user_name_title);
        userNameTitle.setText(user.getName());
    }

    private void initUser() {
        try {
            user = ObjectParser.parseUser(getIntent().getStringExtra("userData"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDateText() {
        dateText = findViewById(R.id.date_text);
        try {
            setDate(new Date(System.currentTimeMillis()),0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private void setDateText(String date){
        dateText.setText(date);
    }
    private String getDate(){
        return dateText.getText().toString();
    }

    private void setDate(Date date, int day) throws ParseException {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        setDateText( ObjectParser.parserDateToString(calendar.getTime()));
    }

    public void nextDay(View view) throws Exception {
        setDate(calendar.getTime(),1);
        List<Task> list = taskService.getAllTasksFromDay(getDate());
    }
    public void previousDay(View view) throws Exception {
        setDate(calendar.getTime(),-1);
        List<Task> list = taskService.getAllTasksFromDay(getDate());
    }

}
