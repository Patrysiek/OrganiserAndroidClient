package com.organiser.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Task {
    private int ID;
    private Date date;
    private String description;

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

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
