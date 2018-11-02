package com.organiser.task;

public class ListViewTaskDTO {
    private boolean checked = false;

    private String taskText = "";

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}
