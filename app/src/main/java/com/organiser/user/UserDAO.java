package com.organiser.user;



import com.organiser.connection.ConnectionHandler;

public class UserDAO {

    private ConnectionHandler connection;

    public  void deleteUser(String login) throws Exception {
        String url = "deleteuser";
        String postData = "login=" + login;

        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }

    public  String createUser(String login, String name, String password) throws Exception {
        String url = "createuser";
        String postData = "login=" + login + "&name=" + name + "&password=" + password;

        connection = new ConnectionHandler(url,postData);
        String userData = connection.readPage();
        connection.close();
        connection.disconnect();
        return userData;
    }

    public  String login(String login, String password) throws Exception {

        String postData = "login=" + login+"&password="+password;
        String url = "login";
        connection = new ConnectionHandler(url,postData);
        String userData = connection.readPage();
        connection.close();
        connection.disconnect();

        return userData;
    }



}
