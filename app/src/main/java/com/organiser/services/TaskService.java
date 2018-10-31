package com.organiser.services;

import com.organiser.task.Task;
import com.organiser.task.TaskDAO;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskDAO taskDAO;

    public TaskService(){
    }
    public TaskService(String tableName){
        this.taskDAO = new TaskDAO(tableName);
    }

public ArrayList<Task> getAllTasksFromDay(String date) throws Exception {
        return taskDAO.getAllTasksFromDay(date);
}

public void insertTask(String date,String description) throws Exception {
        taskDAO.insertTask(date,description);
}
public void deleteTask(int ID) throws Exception {
        taskDAO.deleteTask(ID);
}
public void createTaskTable() throws Exception {
        taskDAO.createTaskTable(taskDAO.getTablename());
}



    /////////////////////GETTERS && SETTERS/////////////////////////////
    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    public void setTaskDAO(String tableName) {
        this.taskDAO = new TaskDAO(tableName);
    }
    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

}
