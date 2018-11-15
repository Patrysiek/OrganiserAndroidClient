package com.organiser.taskList;

import android.widget.CheckBox;
import android.widget.ListView;

import com.organiser.R;
import com.organiser.task.Task;

public class TaskListListener {
    public void setListViewListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task task = (Task)parent.getAdapter().getItem(position);
            CheckBox taskCheckbox = view.findViewById(R.id.list_view_task_checkbox);
            taskCheckbox.setChecked(!task.isChecked());
            task.setChecked(!task.isChecked());
        });
    }
}
