package com.organiser.asyncTasks;


import android.os.AsyncTask;

import com.organiser.asyncTasks.asyncTasksCallbacks.UserRegistererCallback;
import com.organiser.services.SharedTableService;
import com.organiser.services.TaskService;
import com.organiser.services.UserService;
import com.organiser.sharedTable.SharedTableDAO;
import com.organiser.user.UserDAO;


public class UserRegisterer extends AsyncTask<String,Void,String> {

    private UserRegistererCallback callback;
    public UserRegisterer(UserRegistererCallback callback){
        this.callback = callback;
    }
    @Override
    protected String doInBackground(String... strings) {
        TaskService taskService = new TaskService(strings[0]+"table");
        UserService userService = new UserService(new UserDAO());
        String answer;
        try {
            answer = userService.createUser(strings[0],strings[1],strings[2]);
            if(answer.equals("1")){
                 taskService.createTaskTable();
                new SharedTableService(new SharedTableDAO()).createUserSharedTablesTable(strings[0]+"sharedtable");
                return answer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    @Override
    protected void onPostExecute(String answer) {
            callback.responseFromUserRegisterer(answer);
    }
}
