package com.organiser.acitvities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.organiser.R;
import com.organiser.configuration.ActivityConfig;
import com.organiser.services.TaskService;
import com.organiser.somePackage.ObjectParser;
import com.organiser.task.ListViewTaskDTO;
import com.organiser.task.Task;
import com.organiser.task.TaskAdapter;

import com.organiser.user.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView dateText;
    private ArrayList<ListViewTaskDTO> tasksForThisDay;
    private ListView listViewWithCheckbox;
    private TaskAdapter adapter;
    private Calendar calendar;
    private User user;
    private TaskService taskService;

    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfig.setFullScreen(this);
        setContentView(R.layout.activity_main);

        initDeleteButton();
        initUser();
        initUserNameTitle();
        taskService = new TaskService("tabela");
        calendar = Calendar.getInstance();
        initDateText();

        new TaskLoader().execute(getDate());

    }

    private void initDeleteButton() {
        deleteButton = findViewById(R.id.delete_task);

        deleteButton.setOnClickListener((view)-> {
                if(tasksForThisDay!=null){
                    int size = tasksForThisDay.size();
                    for(int i =0; i<size; i++){
                        ListViewTaskDTO dto = tasksForThisDay.get(i);
                        if(dto.isChecked()) {
                            tasksForThisDay.remove(dto);
                            i--;
                            size = tasksForThisDay.size();
                        }
                    }
                }
                initLayoutForTasks();
            });

    }

    private void initUserNameTitle() {
        TextView userNameTitle = findViewById(R.id.user_name_title);
        userNameTitle.setText(user.getName());
    }

    private void initUser() {
            user = ObjectParser.parseUser(getIntent().getStringExtra("userData"));
    }

    private void initDateText() {
        dateText = findViewById(R.id.date_text);
        setDate(new Date(System.currentTimeMillis()),0);
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

    private void initTasksForThisDayList(String date) throws  Exception {
        tasksForThisDay = new ArrayList<>();
            List<Task> list = taskService.getAllTasksFromDay(date);
            for(Task t : list) {
                ListViewTaskDTO dto = new ListViewTaskDTO();
                dto.setTaskText(t.getDescription());
                dto.setChecked(false);
                tasksForThisDay.add(dto);
            }
    }

    private void initLayoutForTasks(){
        listViewWithCheckbox = findViewById(R.id.listview_for_tasks);
        adapter = new TaskAdapter(this,tasksForThisDay);
        adapter.notifyDataSetChanged();
        listViewWithCheckbox.setAdapter(adapter);


        listViewWithCheckbox.setOnItemClickListener((parent, view, position, id) -> {
            ListViewTaskDTO taskDto = (ListViewTaskDTO)parent.getAdapter().getItem(position);
            CheckBox taskCheckbox = view.findViewById(R.id.list_view_task_checkbox);

            taskCheckbox.setChecked(!taskDto.isChecked());
            taskDto.setChecked(!taskDto.isChecked());
        });
    }
/////////////////////////////////GETTERS && SETTERS/////////////////////////////////////////////////
private void setDateText(String date){
    dateText.setText(date);
}
    private String getDate(){
        return dateText.getText().toString();
    }

    private void setDate(Date date, int day) {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        setDateText( ObjectParser.parserDateToString(calendar.getTime()));
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private class TaskLoader extends AsyncTask<String,Void,Void>{


        @Override
        protected Void doInBackground(String... strings) {
            try {
                initTasksForThisDayList(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void value) {
            try {
                initLayoutForTasks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
}
