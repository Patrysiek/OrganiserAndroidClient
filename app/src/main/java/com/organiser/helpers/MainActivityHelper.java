package com.organiser.helpers;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.DatePicker;
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
import java.util.Calendar;


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


    public ListView initLayoutForTasks(int id, ArrayList<Task> arrayList){
        ListView listView = activity.findViewById(id);
        TaskAdapter adapter = new TaskAdapter(activity,arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return listView;
    }

    public void deleteCheckedTask() {
        int size = activity.getTaskListManager().getAllTasksList().size();
        ArrayList<Integer> IDs = new ArrayList<>();
        for(int i =0; i<size; i++){
            Task t = activity.getTaskListManager().getAllTasksList().get(i);
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

    public void setListViewListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task task = (Task)parent.getAdapter().getItem(position);
            CheckBox taskCheckbox = view.findViewById(R.id.list_view_task_checkbox);
            taskCheckbox.setChecked(!task.isChecked());
            task.setChecked(!task.isChecked());
        });
    }

    public void onDialogPositiveClick(String description,String choose) {
        try {
            activity.getTaskService().insertTask(activity.getDate(),description,choose);
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

    public void onDateTextClick() {
        Calendar calendar = activity.getCalendar();
        new DatePickerDialog(activity, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, (DatePicker datePicker, int year, int month, int dayOfMonth)-> {
            calendar.set(year,month,dayOfMonth);
            activity.setDate(calendar.getTime(),0);
            activity.loadTasks();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
