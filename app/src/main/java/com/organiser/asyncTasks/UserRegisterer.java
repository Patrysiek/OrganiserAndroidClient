package com.organiser.asyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.organiser.acitvities.LoginActivity;
import com.organiser.services.TaskService;
import com.organiser.services.UserService;
import com.organiser.user.UserDAO;

import java.lang.ref.WeakReference;

public class UserRegisterer extends AsyncTask<String,Void,String> {

    private WeakReference<Context> contextRef;
    public UserRegisterer(Context context){
        this.contextRef = new WeakReference<>(context);

    }
    @Override
    protected String doInBackground(String... strings) {
        TaskService taskService = new TaskService(strings[0]+"table");
        UserService userService = new UserService(new UserDAO());
        String answer = null;
        try {
            answer = userService.createUser(strings[0],strings[1],strings[2]);
            taskService.createTaskTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
    @Override
    protected void onPostExecute(String answer) {
        Context context = contextRef.get();
        if(!answer.equals("1")) {
            answer = "User already exists !";
            Toast.makeText(context, answer, Toast.LENGTH_SHORT).show();
        }else {
            answer = "User created successfully !";
            Toast.makeText(context, answer, Toast.LENGTH_SHORT).show();
            loadNextActivity();
        }
    }
    private void loadNextActivity() {
        Context context = contextRef.get();
        contextRef.clear();
        new Handler().postDelayed(() -> context.startActivity(new Intent(context, LoginActivity.class)),3000);
    }
}
