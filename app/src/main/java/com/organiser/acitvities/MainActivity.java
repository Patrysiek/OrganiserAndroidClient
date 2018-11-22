package com.organiser.acitvities;



import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.organiser.asyncTasks.TaskAdder;
import com.organiser.asyncTasks.TaskDeleter;
import com.organiser.asyncTasks.asyncTasksCallbacks.ILoadTasksCallback;
import com.organiser.asyncTasks.TaskStatusUpdater;
import com.organiser.dialogs.AddTaskDialog;
import com.organiser.dialogs.dialogsCallbacks.AddTaskDialogCallback;
import com.organiser.R;
import com.organiser.asyncTasks.TaskLoader;
import com.organiser.asyncTasks.asyncTasksCallbacks.ITaskLoaderCallback;
import com.organiser.dialogs.dialogsCallbacks.TaskStatusDialogCallback;
import com.organiser.helpers.DateManager;
import com.organiser.helpers.IsetDateText;
import com.organiser.checkableTaskListView.TaskListListener;
import com.organiser.checkableTaskListView.TaskListViewUpdater;
import com.organiser.taskList.TaskListManager;
import com.organiser.services.TaskService;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AddTaskDialogCallback,ITaskLoaderCallback,IsetDateText,ILoadTasksCallback,TaskStatusDialogCallback {

    private TextView dateText;
    private Calendar calendar;
    private TaskService taskService;
    private TaskListManager taskListManager;
    private DateManager dateManager;
    private List<ListView> listViews;
    private TaskListViewUpdater listViewUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewUpdater = new TaskListViewUpdater(findViewById(android.R.id.content));
        taskService = new TaskService(getIntent().getStringExtra("userNameTable"));
        initListViewList();

        calendar = Calendar.getInstance();
        dateText = findViewById(R.id.date_text);
        dateManager = new DateManager(this);
        dateManager.initDate(calendar,0);


    }

    private void initListViewList() {
        listViews = new ArrayList<>();
        listViews.add((ListView)findViewById(R.id.listview_for_task_in_progress));
        listViews.add((ListView)findViewById(R.id.listview_for_tasks_to_do));
        listViews.add((ListView)findViewById(R.id.listview_for_done_task));
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
    ///////////////////////////////INTERFACES LISTENERS/////////////////////////////////////////////
    @Override
    public void updateTaskListManager(TaskListManager manager) {
        if(manager!=null) {
            taskListManager = manager;
            listViewUpdater.updateListView(taskListManager.getInProgressTaskList(), listViews.get(0));
            listViewUpdater.updateListView(taskListManager.getToDoTasksList(), listViews.get(1));
            listViewUpdater.updateListView(taskListManager.getDoneTasksList(), listViews.get(2));

            TaskListListener listener;
            for (ListView lv : listViews) {
                listener = new TaskListListener(lv);
                listener.setOnTaskLongClickViewListener(getSupportFragmentManager());
                listener.setOnCheckedkListener();
            }
        }
        else Toast.makeText(this,R.string.table_exist_error,Toast.LENGTH_SHORT).show();

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
