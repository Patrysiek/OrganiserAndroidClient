package com.organiser.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Task {
    private int ID;
    private Date date;
    private String description;



    public Task(){

    }
    public Task(int ID,String date, String description) throws ParseException {
        this.ID = ID;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        this.date = format.parse(date);
    }

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


    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
