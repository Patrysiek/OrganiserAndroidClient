package com.organiser.asyncTasks;


import android.os.AsyncTask;
import com.organiser.taskList.TaskListManager;
import com.organiser.services.TaskService;

public class TaskLoader extends AsyncTask<String,Void,TaskListManager>{
    private TaskService taskService;
    private ITaskLoaderCallback listener;
    public TaskLoader(TaskService taskService,ITaskLoaderCallback listener){
        this.taskService = taskService;
        this.listener = listener;
    }
    @Override
    protected TaskListManager doInBackground(String... strings) {
        try {
            return new TaskListManager(taskService.getAllTasksFromDay(strings[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(TaskListManager taskListManager) {
        listener.initTaskListManager(taskListManager);
    }




}

