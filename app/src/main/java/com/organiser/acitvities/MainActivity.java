package com.organiser.acitvities;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.organiser.asyncTasks.TaskAdder;
import com.organiser.asyncTasks.TaskDeleter;
import com.organiser.asyncTasks.ILoadTasksCallback;
import com.organiser.asyncTasks.TaskStatusUpdater;
import com.organiser.dialogs.AddTaskDialog;
import com.organiser.dialogs.AddTaskDialogCallback;
import com.organiser.R;
import com.organiser.asyncTasks.TaskLoader;
import com.organiser.asyncTasks.ITaskLoaderCallback;
import com.organiser.dialogs.TaskStatusDialogCallback;
import com.organiser.helpers.DateManager;
import com.organiser.helpers.IsetDateText;
import com.organiser.helpers.LoginChecker;
import com.organiser.helpers.ObjectParser;
import com.organiser.taskList.TaskListLayoutManager;
import com.organiser.taskList.TaskListManager;
import com.organiser.services.TaskService;

import com.organiser.user.User;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AddTaskDialogCallback,ITaskLoaderCallback,IsetDateText,ILoadTasksCallback,TaskStatusDialogCallback {

    private TextView dateText;
    private Calendar calendar;
    private TaskService taskService;
    private TaskListManager taskListManager;
    private DateManager dateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = ObjectParser.parseUser(LoginChecker.getUser(this));

        TextView userNameTitle = findViewById(R.id.user_name_title);
        if(user!=null) {
            userNameTitle.setText(user.getName());
            taskService = new TaskService(user.getLogin() + "table");
        }
        else {
            userNameTitle.setText(R.string.error);
            taskService = new TaskService("");
        }

        calendar = Calendar.getInstance();
        dateText = findViewById(R.id.date_text);
        dateManager = new DateManager(this);
        dateManager.initDate(calendar,0);
    }
    ///////////////////////////////BUTTONS LISTENERS////////////////////////////////////////////////
    public void switchDayButtonListener(View view) {
        dateManager.switchDay(view.getId(),calendar);
    }
    public void addNewTaskButtonListener(View view){
        DialogFragment newFragment = new AddTaskDialog();
        newFragment.show(getSupportFragmentManager(), "addTaskDialog");
    }
    public void deleteCheckedTaskButtonListener(View view){
        new TaskDeleter(taskService,taskListManager,this).execute();
    }
    public void DateTextListener(View view){
        dateManager.onDateTextClick(calendar,this);
    }
    public void logoutButtonListener(View v){
        LoginChecker.clearPrefs(this);
        Toast.makeText(this,"Logout successfully !",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));
    }
    ///////////////////////////////INTERFACES LISTENERS/////////////////////////////////////////////
    @Override
    public void initTaskListManager(TaskListManager taskListManager) {
        this.taskListManager = taskListManager;
        new TaskListLayoutManager(taskListManager,findViewById(android.R.id.content),getSupportFragmentManager()).setTaskListLayout();
    }
    @Override
    public void onAddTaskDialogPositiveClick(String  description, String choose) {
        new TaskAdder(this,taskService).execute(dateText.getText().toString(),description,choose);
    }
    @Override
    public void loadTasksAfterChange() {
        new TaskLoader(taskService,this).execute(dateText.getText().toString());
    }
    @Override
    public void setDateText(String date){
        dateText.setText(date);
    }
    @Override
    public void statusTaskDialogClick(String status,int ID) {
        new TaskStatusUpdater(taskService,this).execute(String.valueOf(ID),status);
    }
}
