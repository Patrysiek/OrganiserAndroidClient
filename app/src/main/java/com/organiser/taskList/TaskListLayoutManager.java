package com.organiser.taskList;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ListView;

import com.organiser.R;
import com.organiser.task.Task;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TaskListLayoutManager {

    private TaskListManager taskListManager;
    private WeakReference<View> viewWeakReference;
    private FragmentManager manager;
    public TaskListLayoutManager(TaskListManager taskListManager, View view, FragmentManager manager){
        this.taskListManager = taskListManager;
        this.viewWeakReference = new WeakReference<>(view);
        this.manager = manager;
    }

    public void setTaskListLayout(){
        ListView listView;
        try {
            if(taskListManager.getToDoTasksList()!=null){
                listView = initLayoutForTasks(R.id.listview_for_tasks_to_do,taskListManager.getToDoTasksList());
                new TaskListListener().setListViewListener(listView,manager);
            }
            if(taskListManager.getInProgressTaskList()!=null){
                listView = initLayoutForTasks(R.id.listview_for_done_task,taskListManager.getInProgressTaskList());
                new TaskListListener().setListViewListener(listView,manager);
            }
            if(taskListManager.getDoneTasksList()!=null){
                listView = initLayoutForTasks(R.id.listview_for_task_in_progress,taskListManager.getDoneTasksList());
                new TaskListListener().setListViewListener(listView,manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ListView initLayoutForTasks(int id, ArrayList<Task> arrayList){
        View view = viewWeakReference.get();
        ListView listView = view.findViewById(id);
        TaskAdapter adapter = new TaskAdapter(view.getContext(),arrayList);

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return listView;
    }

}
