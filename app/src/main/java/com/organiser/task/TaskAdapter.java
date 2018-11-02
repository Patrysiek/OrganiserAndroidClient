package com.organiser.task;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.organiser.R;

import java.util.List;

public class TaskAdapter extends BaseAdapter {


    private List<TaskDTOforListView> taskDTOList;

    private Context ctx;

    public TaskAdapter(Context ctx, List<TaskDTOforListView> taskDTOList) {
        this.ctx = ctx;
        this.taskDTOList = taskDTOList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(taskDTOList !=null)
        {
            ret = taskDTOList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if(taskDTOList !=null) {
            ret = taskDTOList.get(itemIndex);
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

        TaskDTOforListView listViewItemDto = taskDTOList.get(itemIndex);
        viewHolder.getTaskCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getTaskTextView().setText(listViewItemDto.getTaskText());

        return convertView;
    }
}
