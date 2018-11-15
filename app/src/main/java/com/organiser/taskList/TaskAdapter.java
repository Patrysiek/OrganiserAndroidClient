package com.organiser.taskList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.organiser.R;
import com.organiser.task.ListViewTaskHolder;
import com.organiser.task.Task;

import java.util.List;

public class TaskAdapter extends BaseAdapter {


    private List<Task> taskList;

    private Context ctx;

    public TaskAdapter(Context ctx, List<Task> taskList) {
        this.ctx = ctx;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(taskList !=null)
        {
            ret = taskList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if(taskList !=null) {
            ret = taskList.get(itemIndex);
        }
        return ret;
    }

    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        ListViewTaskHolder viewHolder;

        if(convertView!=null)
        {
            viewHolder = (ListViewTaskHolder) convertView.getTag();
        }else
        {
            convertView = View.inflate(ctx, R.layout.list_item, null);

            CheckBox listTaskCheckbox = convertView.findViewById(R.id.list_view_task_checkbox);

            TextView listTaskText = convertView.findViewById(R.id.list_view_task_text);

            viewHolder = new ListViewTaskHolder(convertView);
            viewHolder.setTaskCheckbox(listTaskCheckbox);
            viewHolder.setTaskTextView(listTaskText);

            convertView.setTag(viewHolder);
        }

        Task listViewItemDto = taskList.get(itemIndex);
        viewHolder.getTaskCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getTaskTextView().setText(listViewItemDto.getDescription());

        return convertView;
    }
}
