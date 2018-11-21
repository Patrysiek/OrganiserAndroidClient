package com.organiser.checkableListView;



import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.widget.CheckBox;
import android.widget.ListView;

import com.organiser.R;

import com.organiser.acitvities.MainActivity;
import com.organiser.dialogs.TaskStatusDialog;

public class CustomListListener<T extends  CheckableAndDescriptionable> {

        private ListView listView;
        public CustomListListener(ListView listView){
                this.listView = listView;
        }

        public void setOnTaskLongClickViewListener(FragmentManager manager) {
                listView.setOnItemLongClickListener((parent, view, position, id) -> {
                        T t = (T) parent.getAdapter().getItem(position);
                        new TaskStatusDialog(t.getID()).show(manager, "task_status_dialog_tag");
                        return true;
                });
        }

        public void setOnCheckedkListener() {
                listView.setOnItemClickListener((parent, view, position, id) -> {
                        T t = (T) parent.getAdapter().getItem(position);
                        CheckBox tCheckbox = view.findViewById(R.id.list_view_task_checkbox);
                        tCheckbox.setChecked(!t.isChecked());
                        t.setChecked(!t.isChecked());
                });

        }
        public void setOnSharedTableLongClickViewListener() {
                listView.setOnItemLongClickListener((parent, view, position, id) -> {
                        T t = (T) parent.getAdapter().getItem(position);
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.putExtra("userNameTable",t.getDesc());
                        view.getContext().startActivity(intent);
                        return true;
                });
        }
}
