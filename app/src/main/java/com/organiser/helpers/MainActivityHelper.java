package com.organiser.helpers;

import android.widget.ListView;
import android.widget.TextView;

import com.organiser.R;
import com.organiser.acitvities.MainActivity;
import com.organiser.task.Task;
import com.organiser.task.TaskAdapter;
import com.organiser.user.User;

import java.util.ArrayList;


public class MainActivityHelper {
    private MainActivity activity;

    public MainActivityHelper(MainActivity activity){
        this.activity = activity;
    }

    public TextView initUserNameTitle() {
        return activity.findViewById(R.id.user_name_title);
    }

    public User initUser() {
        return  ObjectParser.parseUser(LoginChecker.getUser(activity));
    }

    public TextView initDateText() {
        return activity.findViewById(R.id.date_text);
    }

    public ArrayList<Task> initTasksForThisDayList(String date){

        return activity.getTaskService().getAllTasksFromDay(date);
    }

    public ListView initLayoutForTasks(ArrayList<Task> arrayList){
        ListView listView = activity.findViewById(R.id.listview_for_tasks);
        TaskAdapter adapter = new TaskAdapter(activity,arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return listView;
    }
}
