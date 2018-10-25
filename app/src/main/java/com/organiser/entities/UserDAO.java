package com.organiser.entities;

import com.organiser.connection.ConnectionHandler;

public class UserDAO {

    private ConnectionHandler connection;
    private String url;

    public UserDAO(){
        url ="http://10.0.2.2:8080/OrganiserWebService/";
    }


    public  void deleteUser(String login) throws Exception {
        String url = "http://localhost:8080/OrganiserWebService/deleteuser";
        String postData = "login=" + login;

        connection = new ConnectionHandler(url,postData);
    }

    public  void createUser(String login, String name, String password) throws Exception {
        String url = this.url+"createuser";
        String postData = "login=" + login + "&name=" + name + "&password=" + password;

        connection = new ConnectionHandler(url,postData);

    }

    public  void createUserTable(String tablename) throws Exception {
        String url = this.url+"createusertable";
        String postData = "tablename="+tablename;
        connection = new ConnectionHandler(url,postData);

    }
    public  void dropUserTable(String tablename) throws Exception {
        String url = this.url+"dropusertable";
        String postData = "tablename="+tablename;
        connection = new ConnectionHandler(url,postData);

    }

    public  void getAllUsers() throws Exception {

        String url = this.url+"users";
        connection = new ConnectionHandler(url,"");
    }

    public  String login(String login, String password) throws Exception {

        String postData = "login=" + login+"&password="+password;
        String url = this.url+"login";
        connection = new ConnectionHandler(url,postData);
        String userData = connection.readPage();
        connection.disconnect();

        return userData;
    }
}
