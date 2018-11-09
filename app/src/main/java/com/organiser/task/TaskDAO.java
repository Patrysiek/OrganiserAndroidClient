package com.organiser.task;


import com.organiser.connection.ConnectionHandler;
import com.organiser.helpers.ObjectParser;

import java.util.ArrayList;


public class TaskDAO {

    private ConnectionHandler connection;
    private String url;
    private String tablename;

    public TaskDAO(String tablename){


        url ="http://192.168.0.104:8080/OrganiserWebService/";
        this.tablename = "tablename="+tablename;
    }


    public  void deleteTask(ArrayList<Integer> ID) throws Exception {
        String url = this.url+"deletetask";
        StringBuilder IDs = new StringBuilder();


        for(int i=0; i<ID.size(); i++){
            if(i>0)
                IDs.append(",");

            IDs.append(ID.get(i));
        }

        String postData = tablename+"&ID=" + IDs.toString();

        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }

    public  void insertTask(String date,String description,String choose) throws Exception {
        String url = this.url+"inserttask";
        String postData = tablename+"&date=" + date+"&description="+description+"&status="+choose;

        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }

    public  ArrayList<Task> getAllTasksFromDay(String date) throws Exception {
        String url = this.url+"alltasksfromday";
        String postData = tablename+"&date=" + date;

        connection = new ConnectionHandler(url,postData);
        ArrayList<Task> taskList = ObjectParser.parserTaskList(connection.readPage());
        connection.close();
        connection.disconnect();
        return taskList;
    }
    public void createTaskTable()throws Exception {
        String url = this.url+"createtasktable";
        String postData = this.tablename;

        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }


    public String getTablename() {
        return tablename;
    }

}
