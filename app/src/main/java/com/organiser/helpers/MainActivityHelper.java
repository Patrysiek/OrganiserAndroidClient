package com.organiser.helpers;

import android.content.Intent;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.acitvities.LoginActivity;
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


    public void initLayoutForTasks(int id, ArrayList<Task> arrayList){
        ListView listView = activity.findViewById(id);
        TaskAdapter adapter = new TaskAdapter(activity,arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        activity.setListViewForDoneTasks(listView);
    }

    public void deleteCheckedTask() {
        int size = activity.getTaskListManager().getToDoTasksList().size();
        ArrayList<Integer> IDs = new ArrayList<>();
        for(int i =0; i<size; i++){
            Task t = activity.getTaskListManager().getToDoTasksList().get(i);
            if(t.isChecked()) {
                IDs.add(t.getID());
            }
        }
        try {
            activity.getTaskService().deleteTask(IDs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListViewListener() {
        activity.getListViewForDoneTasks().setOnItemClickListener((parent, view, position, id) -> {
            Task taskDto = (Task)parent.getAdapter().getItem(position);
            CheckBox taskCheckbox = view.findViewById(R.id.list_view_task_checkbox);
            taskCheckbox.setChecked(!taskDto.isChecked());
            taskDto.setChecked(!taskDto.isChecked());
        });
    }

    public void onDialogPositiveClick(String description) {
        try {
            activity.getTaskService().insertTask(activity.getDate(),description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        LoginChecker.clearPrefs(activity);
        Toast.makeText(activity,"Logout successfully !",Toast.LENGTH_SHORT).show();
        activity.startActivity(new Intent(activity,LoginActivity.class));
    }

    public void switchDay(int id) {
        switch (id) {
            case R.id.next_date_button:
                activity.setDate(activity.getCalendar().getTime(), 1);
                break;
            case R.id.previous_date_button:
                activity.setDate(activity.getCalendar().getTime(), -1);
                break;
        }
    }
}
