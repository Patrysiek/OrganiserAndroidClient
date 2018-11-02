package com.organiser.helpers;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.organiser.R;
import com.organiser.acitvities.MainActivity;
import com.organiser.task.TaskDTOforListView;
import com.organiser.task.Task;
import com.organiser.task.TaskAdapter;
import com.organiser.user.User;

import java.util.ArrayList;
import java.util.List;


public class MainActivityHelper {
    private MainActivity activity;

    public MainActivityHelper(MainActivity activity){
        this.activity = activity;
    }

    public TextView initUserNameTitle() {
        return activity.findViewById(R.id.user_name_title);
    }

    public User initUser() {
        return  ObjectParser.parseUser(activity.getIntent().getStringExtra("userData"));
    }

    public TextView initDateText() {
        return activity.findViewById(R.id.date_text);
    }

    public ArrayList<TaskDTOforListView> initTasksForThisDayList(String date) throws  Exception {
        ArrayList<TaskDTOforListView> tasksForThisDay = new ArrayList<>();
        List<Task> list = activity.getTaskService().getAllTasksFromDay(date);
        for(Task t : list) {
            TaskDTOforListView dto = new TaskDTOforListView();
            dto.setTaskText(t.getDescription());
            dto.setID(t.getID());
            dto.setChecked(false);
            tasksForThisDay.add(dto);
        }
        return tasksForThisDay;
    }

    public ListView initLayoutForTasks(ArrayList<TaskDTOforListView> arrayList){
        ListView listView = activity.findViewById(R.id.listview_for_tasks);
        TaskAdapter adapter = new TaskAdapter(activity,arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return listView;
    }

    public Button initAddButton() {
        return activity.findViewById(R.id.add_task);
    }
    public Button initEditButton(){
        return activity.findViewById(R.id.edit_task);
    }
    public Button initDeleteButton() {
        return activity.findViewById(R.id.delete_task);
    }
}
