package com.organiser.services;

import com.organiser.task.Task;
import com.organiser.task.TaskDAO;

import java.util.ArrayList;

public class TaskService {
    private TaskDAO taskDAO;


    public TaskService(String tableName){
        this.taskDAO = new TaskDAO(tableName);
    }

    public ArrayList<Task> getAllTasksFromDay(String date) {
        try {
            return taskDAO.getAllTasksFromDay(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertTask(String date,String description) throws Exception {
        taskDAO.insertTask(date,description);
    }
    public void deleteTask(ArrayList<Integer> IDs) throws Exception {
        taskDAO.deleteTask(IDs);
    }
    public void createTaskTable() throws Exception {
        taskDAO.createTaskTable();
    }


}
