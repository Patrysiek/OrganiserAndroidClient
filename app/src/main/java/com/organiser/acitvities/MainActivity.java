package com.organiser.acitvities;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


import com.organiser.Dialogs.AddTaskDialog;
import com.organiser.R;
import com.organiser.configuration.ActivityConfig;
import com.organiser.services.TaskService;
import com.organiser.helpers.MainActivityHelper;
import com.organiser.helpers.ObjectParser;
import com.organiser.task.TaskDTOforListView;

import com.organiser.user.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddTaskDialog.TaskDialogListener{

    private TextView dateText;

    private ArrayList<TaskDTOforListView> tasksForThisDay;
    private ListView listViewWithCheckbox;
    private Calendar calendar;

    private User user;
    private TaskService taskService;
    private MainActivityHelper helper;
    private Button addButton,editButton,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfig.setFullScreen(this);
        setContentView(R.layout.activity_main);
        helper = new MainActivityHelper(this);

        user = helper.initUser();
        TextView userNameTitle = helper.initUserNameTitle();
        userNameTitle.setText(user.getName());

        taskService = new TaskService("tabela");
        calendar = Calendar.getInstance();
        dateText = helper.initDateText();

        addButton = helper.initAddButton();
        deleteButton = helper.initDeleteButton();
        editButton = helper.initEditButton();

        setDate(new Date(System.currentTimeMillis()),0);
        new TaskLoader().execute(getDate());
        setAddButtonListener();
        setDeleteButtonListener();
    }
    ///////////////////////////////LISTENERS/////////////////////////////////////////////////////////
    private void setAddButtonListener() {
        addButton.setOnClickListener((view) ->{
            DialogFragment newFragment = new AddTaskDialog();
            newFragment.show(getSupportFragmentManager(), "addTaskDialog");
        });
    }
    public void switchDay(View view) {
        switch (view.getId()) {
            case R.id.next_date_button:
                setDate(calendar.getTime(), 1);
                break;
            case R.id.previous_date_button:
                setDate(calendar.getTime(), -1);
                break;
        }
        new TaskLoader().execute(getDate());
    }
    private void setDeleteButtonListener() {
        deleteButton.setOnClickListener((view)-> {
            int size = tasksForThisDay.size();
            ArrayList<Integer> IDs = new ArrayList<>();
            for(int i =0; i<size; i++){
                TaskDTOforListView dto = tasksForThisDay.get(i);
                if(dto.isChecked()) {
                    IDs.add(dto.getID());
                }
            }
            try {
                taskService.deleteTask(IDs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TaskLoader().execute(getDate());
        });
    }

    public void setListViewListener(){
        listViewWithCheckbox.setOnItemClickListener((parent, view, position, id) -> {
            TaskDTOforListView taskDto = (TaskDTOforListView)parent.getAdapter().getItem(position);
            CheckBox taskCheckbox = view.findViewById(R.id.list_view_task_checkbox);

            taskCheckbox.setChecked(!taskDto.isChecked());
            taskDto.setChecked(!taskDto.isChecked());
        });
    }
    @Override
    public void onDialogPositiveClick(String  description) {
        try {
            taskService.insertTask(getDate(),description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new TaskLoader().execute(getDate());
    }

    /////////////////////////////////GETTERS && SETTERS/////////////////////////////////////////////
    public void setDateText(String date){
        dateText.setText(date);
    }
    private String getDate(){
        return dateText.getText().toString();
    }

    public void setDate(Date date, int day) {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        setDateText( ObjectParser.parserDateToString(calendar.getTime()));
    }
    public TaskService getTaskService() {
        return taskService;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private class TaskLoader extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            try {
                tasksForThisDay = helper.initTasksForThisDayList(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void value) {
            try {
                listViewWithCheckbox = helper.initLayoutForTasks(tasksForThisDay);
                setListViewListener();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
}
