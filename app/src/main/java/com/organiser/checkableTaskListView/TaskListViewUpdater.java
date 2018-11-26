package com.organiser.checkableTaskListView;

import android.view.View;
import android.widget.ListView;

import com.organiser.task.Task;

import java.lang.ref.WeakReference;
import java.util.List;

public class TaskListViewUpdater {

    private WeakReference<View> viewWeakReference;
    public TaskListViewUpdater(View view){
        this.viewWeakReference = new WeakReference<>(view);
    }
    public void updateListView(List<Task> arrayList, ListView listView){
        View view = viewWeakReference.get();
        TaskAdapter adapter = new TaskAdapter(view.getContext(), arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

}
