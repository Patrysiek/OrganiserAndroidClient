package com.organiser.task;


import com.organiser.connection.ConnectionHandler;
import com.organiser.helpers.ObjectParser;

import java.util.ArrayList;


public class TaskDAO {

    private ConnectionHandler connection;
    private String tablename;

    public TaskDAO(String tablename){
        this.tablename = "tablename="+tablename;
    }
    public  void deleteTask(ArrayList<Integer> ID) throws Exception {
        String url = "deletetask";
        StringBuilder IDs = new StringBuilder();


        for(int i=0; i<ID.size(); i++){
            if(i>0)
                IDs.append(",");

            IDs.append(ID.get(i));
        }

        String postData = tablename+"&ID=" + IDs.toString();

        performConnection(url,postData);
    }

    public  void insertTask(String date,String description,String choose) throws Exception {
        String url = "inserttask";
        String postData = tablename+"&date=" + date+"&description="+description+"&status="+choose;

        performConnection(url,postData);
    }

    public  ArrayList<Task> getAllTasksFromDay(String date) throws Exception {
        String url = "alltasksfromday";
        String postData = tablename+"&date=" + date;
        connection = new ConnectionHandler(url,postData);
        ArrayList<Task> taskList = ObjectParser.parserTaskList(connection.readPage());
        connection.close();
        connection.disconnect();
        return taskList;
    }
    public void createTaskTable()throws Exception {
        String url = "createtasktable";
        String postData = this.tablename;

        performConnection(url,postData);
    }

    public void updateTask(String ID, String status) throws Exception{
        String url = "updatetask";
        String postData = tablename+"&ID=" +ID+"&status="+status;

        performConnection(url,postData);
    }

    public void performConnection(String url, String postData)throws Exception{
        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }

}
