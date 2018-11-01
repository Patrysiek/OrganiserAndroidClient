package com.organiser.task;



import com.organiser.connection.ConnectionHandler;
import com.organiser.somePackage.ObjectParser;

import java.util.ArrayList;


public class TaskDAO {


    private ConnectionHandler connection;
    private String url;
    private String tablename;

    public TaskDAO(String tablename){
        url ="http://10.0.2.2:8080/OrganiserWebService/";
        this.tablename = "tablename="+tablename;
    }



    public  void deleteTask(int ID) throws Exception {
        String url = this.url+"deletetask";
        String postData = tablename+"&ID=" + ID;


        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }

    public  void insertTask(String date,String description) throws Exception {
        String url = this.url+"inserttask";
        String postData = tablename+"&date=" + date+"&description="+description;

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
