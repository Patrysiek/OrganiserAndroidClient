package com.organiser.acitvities;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.organiser.Dialogs.AddTaskDialog;
import com.organiser.R;
import com.organiser.services.TaskListManager;
import com.organiser.services.TaskService;
import com.organiser.helpers.MainActivityHelper;
import com.organiser.helpers.ObjectParser;

import com.organiser.user.User;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AddTaskDialog.TaskDialogListener{

    private TextView dateText;

    private TaskListManager taskListManager;
    private ListView listViewForDoneTasks,listViewForTaskToDo,listViewForTasksInProgress;
    private Calendar calendar;

    private TaskService taskService;
    private MainActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MainActivityHelper(this);

        User user = helper.initUser();
        TextView userNameTitle = helper.initUserNameTitle();
        userNameTitle.setText(user.getName());

        taskService = new TaskService(user.getLogin()+"table");

        calendar = Calendar.getInstance();
        dateText = helper.initDateText();

        setDate(new Date(System.currentTimeMillis()),0);
        loadTasks();
    }
    ///////////////////////////////LISTENERS/////////////////////////////////////////////////////////
    public void switchDay(View view) {
        helper.switchDay(view.getId());
        loadTasks();
    }
    public void addNewTask(View view){
        DialogFragment newFragment = new AddTaskDialog();
        newFragment.show(getSupportFragmentManager(), "addTaskDialog");
    }
    public void deleteCheckedTask(View view){
        helper.deleteCheckedTask();
        loadTasks();
    }
    public void setListViewListener(){
        if(taskListManager.getDoneTasksList().size()>0)
        helper.setListViewListener(listViewForDoneTasks);
        if(taskListManager.getToDoTasksList().size()>0)
        helper.setListViewListener(listViewForTaskToDo);
        if(taskListManager.getInProgressTaskList().size()>0)
        helper.setListViewListener(listViewForTasksInProgress);
    }
    public void DateTextListener(View view){
        helper.onDateTextClick();
    }

    public void loadTasks(){
        new TaskLoader().execute(getDate());
    }
    @Override
    public void onDialogPositiveClick(String  description,String choose) {
        helper.onDialogPositiveClick(description,choose);
        loadTasks();
    }

    public void logout(View v){
    helper.logout();
    }
    /////////////////////////////////GETTERS && SETTERS/////////////////////////////////////////////
    public String getDate(){
        return dateText.getText().toString();
    }
    public void setDate(Date date, int day) {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        setDateText( ObjectParser.parserDateToString(calendar.getTime()));
        setDateText( ObjectParser.parserDateToString(calendar.getTime()));
    }
    public void setDateText(String date){
        dateText.setText(date);
    }
    public TaskService getTaskService() {
        return taskService;
    }
    public Calendar getCalendar() {
        return calendar;
    }
    public TaskListManager getTaskListManager() {
        return taskListManager;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public class TaskLoader extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... strings) {
            try {
                taskListManager = new TaskListManager(taskService.getAllTasksFromDay(strings[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void value) {
            try {
                if(taskListManager.getToDoTasksList()!=null)
                listViewForTaskToDo = helper.initLayoutForTasks(R.id.listview_for_tasks_to_do,taskListManager.getToDoTasksList());
                if(taskListManager.getInProgressTaskList()!=null)
                listViewForTasksInProgress = helper.initLayoutForTasks(R.id.listview_for_task_done_task,taskListManager.getInProgressTaskList());
                if(taskListManager.getDoneTasksList()!=null)
                listViewForDoneTasks = helper.initLayoutForTasks(R.id.listview_for_task_in_progress,taskListManager.getDoneTasksList());
                setListViewListener();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
}
