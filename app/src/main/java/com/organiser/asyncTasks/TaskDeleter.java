package com.organiser.asyncTasks;

import android.os.AsyncTask;

import com.organiser.services.TaskService;
import com.organiser.task.Task;
import com.organiser.taskList.TaskListManager;

import java.util.ArrayList;

public class TaskDeleter extends AsyncTask<Void,Void,Void> {

    private TaskListManager taskListManager;
    private TaskService taskService;
    private ILoadTasksCallback listener;


    public TaskDeleter(TaskService taskService, TaskListManager taskListManager,ILoadTasksCallback listener){
        this.taskListManager = taskListManager;
        this.taskService = taskService;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int size = taskListManager.getAllTasksList().size();
        ArrayList<Integer> IDs = new ArrayList<>();
        for(int i =0; i<size; i++){
            Task t = taskListManager.getAllTasksList().get(i);
            if(t.isChecked()) {
                IDs.add(t.getID());
            }
        }
        try {
            taskService.deleteTask(IDs);
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
