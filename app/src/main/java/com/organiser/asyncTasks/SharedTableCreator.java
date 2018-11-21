package com.organiser.asyncTasks;

import android.os.AsyncTask;

import com.organiser.asyncTasks.asyncTasksCallbacks.LoadSharedTableListener;
import com.organiser.services.SharedTableService;
import com.organiser.sharedTable.SharedTableDAO;

public class SharedTableCreator extends AsyncTask<String,Void,Void> {

    LoadSharedTableListener listener;

    public SharedTableCreator(LoadSharedTableListener listener){
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(String... strings) {
        SharedTableService sharedTableService = new SharedTableService(new SharedTableDAO());
        try {
            sharedTableService.addNewTableToSharedTables(strings[0],strings[1],strings[2]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.loadSharedTables();
    }
}
