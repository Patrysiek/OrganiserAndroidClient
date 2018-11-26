package com.organiser.checkableTaskListView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class TaskListViewHolder extends RecyclerView.ViewHolder{
    private CheckBox checkbox;
    private TextView textView;

    public TaskListViewHolder(View taskView) {
        super(taskView);
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
