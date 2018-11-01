package com.organiser.services;

import com.organiser.user.UserDAO;

public class UserService {
    private UserDAO userDAO;


    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }


    public String login(String login,String password ) throws Exception {
        return userDAO.login(login,password);
    }
    public void deleteUser(String login) throws Exception {
        userDAO.deleteUser(login);
    }
    public void createUser(String login,String username,String password) throws Exception {
        userDAO.createUser(login, username, password);
    }
}
