package com.organiser.task;

import java.util.Date;


public class Task implements CheckableAndDescriptionable{
    private int ID;
    private Date date;
    private String description;
    private String status;

    private boolean checked = false;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
