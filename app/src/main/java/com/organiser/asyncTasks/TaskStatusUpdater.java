package com.organiser.asyncTasks;


import android.os.AsyncTask;

import com.organiser.asyncTasksCallbacks.ILoadTasksCallback;
import com.organiser.services.TaskService;

public class TaskStatusUpdater extends AsyncTask<String,Void,Void>{
    private TaskService taskService;
    private ILoadTasksCallback listener;
    public TaskStatusUpdater(TaskService taskService, ILoadTasksCallback listener){
        this.taskService = taskService;
        this.listener = listener;
    }
    @Override
    protected Void doInBackground(String... strings) {
        try {
            taskService.updateTask(strings[0],strings[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        listener.loadTasksAfterChange();
    }

}

