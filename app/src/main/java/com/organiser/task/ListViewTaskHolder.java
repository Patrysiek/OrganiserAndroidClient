package com.organiser.task;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListViewTaskHolder extends RecyclerView.ViewHolder{
    private CheckBox taskCheckbox;

    private TextView taskTextView;

    public ListViewTaskHolder(View taskView) {
        super(taskView);
    }

    public CheckBox getTaskCheckbox() {
        return taskCheckbox;
    }

    public void setTaskCheckbox(CheckBox taskCheckbox) {
        this.taskCheckbox = taskCheckbox;
    }

    public TextView getTaskTextView() {
        return taskTextView;
    }

    public void setTaskTextView(TextView taskTextView) {
        this.taskTextView = taskTextView;
    }
}
