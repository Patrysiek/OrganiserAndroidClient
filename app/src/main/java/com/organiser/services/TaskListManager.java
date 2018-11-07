package com.organiser.services;

import com.organiser.task.Task;

import java.util.ArrayList;

public class TaskListManager {
    private ArrayList<Task> allTasksList,doneTasksList,toDoTasksList,inProgressTaskList;


    public TaskListManager(ArrayList<Task> allTasksList){
        this.allTasksList = allTasksList;
        doneTasksList = new ArrayList<>();
        toDoTasksList = new ArrayList<>();
        inProgressTaskList = new ArrayList<>();
       groupLists();
    }

    private void groupLists() {

        for(Task t : allTasksList){

            switch (t.getStatus()){
                case "ToDo":
                    toDoTasksList.add(t);
                    break;
                case "inProgress":
                    toDoTasksList.add(t);
                    break;
                case "done":
                    toDoTasksList.add(t);
                    break;
            }
        }
    }

    public ArrayList<Task> getDoneTasksList() {
        return doneTasksList;
    }

    public ArrayList<Task> getInProgressTaskList() {
        return inProgressTaskList;
    }

    public ArrayList<Task> getToDoTasksList() {
        return toDoTasksList;
    }
}
