package com.organiser.asyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.organiser.acitvities.MainActivity;
import com.organiser.helpers.LoginChecker;
import com.organiser.services.UserService;
import com.organiser.user.UserDAO;

import java.lang.ref.WeakReference;

public class LoginTheUser extends AsyncTask<String,Void,String> {

    private UserService userService;

    private WeakReference<Context> contextRef;

    public LoginTheUser(UserDAO userDAO, Context context) {
        this.userService = new UserService(userDAO);
        contextRef = new WeakReference<>(context);

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
        Context context = contextRef.get();
        if (s != null) {
            if (s.length() > 0) {
                startMainActivity(s);
            } else {
                Toast toast = Toast.makeText(context, "You pass invalid login or password", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        Toast.makeText(context, "Server error ! ", Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity(String userData) {
        Context context = contextRef.get();
        contextRef.clear();
        LoginChecker.saveUser(context,userData);
        context.startActivity(new Intent(context,MainActivity.class));
    }
}