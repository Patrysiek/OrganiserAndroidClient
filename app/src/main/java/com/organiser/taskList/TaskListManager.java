package com.organiser.taskList;

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
                    inProgressTaskList.add(t);
                    break;
                case "done":
                    doneTasksList.add(t);
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

    public ArrayList<Task> getAllTasksList() {
        return allTasksList;
    }
}
