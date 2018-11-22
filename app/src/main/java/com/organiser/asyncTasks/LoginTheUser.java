package com.organiser.asyncTasks;

import android.os.AsyncTask;

import com.organiser.asyncTasks.asyncTasksCallbacks.LoginTheUserCallback;
import com.organiser.services.UserService;
import com.organiser.user.UserDAO;


public class LoginTheUser extends AsyncTask<String,Void,String> {

    private UserService userService;
    private LoginTheUserCallback listener;

    public LoginTheUser(UserDAO userDAO, LoginTheUserCallback listener) {
        this.userService = new UserService(userDAO);
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            return userService.login(strings[0], strings[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            if (s.length() > 0) {
                listener.startTaskChoiceActivity(s);
            } else {

                listener.invalidLoginOrPassword();
            }
        }
        else
            listener.serverError();
    }

}