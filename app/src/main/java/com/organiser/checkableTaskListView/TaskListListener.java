package com.organiser.checkableTaskListView;



import android.support.v4.app.FragmentManager;
import android.widget.CheckBox;
import android.widget.ListView;

import com.organiser.R;

import com.organiser.dialogs.TaskStatusDialog;
import com.organiser.task.Task;

public class TaskListListener {

        private ListView listView;
        public TaskListListener(ListView listView){
                this.listView = listView;
        }

        public void setOnTaskLongClickViewListener(FragmentManager manager) {
                listView.setOnItemLongClickListener((parent, view, position, id) -> {
                        Task t = (Task) parent.getAdapter().getItem(position);
                        new TaskStatusDialog(t.getID()).show(manager, "task_status_dialog_tag");
                        return true;
                });
        }

        public void setOnCheckedkListener() {
                listView.setOnItemClickListener((parent, view, position, id) -> {
                        Task t = (Task)parent.getAdapter().getItem(position);
                        CheckBox tCheckbox = view.findViewById(R.id.list_view_task_checkbox);
                        tCheckbox.setChecked(!t.isChecked());
                        t.setChecked(!t.isChecked());
                });

        }
}
