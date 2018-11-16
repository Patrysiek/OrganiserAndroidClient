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

    public void insertTask(String date,String description,String choose) throws Exception {
        taskDAO.insertTask(date,description,choose);
    }
    public void deleteTask(ArrayList<Integer> IDs) throws Exception {
        taskDAO.deleteTask(IDs);
    }
    public void createTaskTable() throws Exception {
        taskDAO.createTaskTable();
    }

    public void updateTask(String ID, String status)throws Exception {
        taskDAO.updateTask(ID,status);
    }
}
