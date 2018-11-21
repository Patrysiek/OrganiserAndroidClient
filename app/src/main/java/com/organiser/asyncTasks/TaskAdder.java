package com.organiser.asyncTasks;

import android.os.AsyncTask;

import com.organiser.asyncTasks.asyncTasksCallbacks.ILoadTasksCallback;
import com.organiser.services.TaskService;

public class TaskAdder extends AsyncTask<String,Void,Void> {

    private ILoadTasksCallback listener;
    private TaskService taskService;

    public TaskAdder(ILoadTasksCallback listener, TaskService taskService){
        this.listener = listener;
        this.taskService = taskService;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            taskService.insertTask(strings[0],strings[1],strings[2]);
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
