package com.organiser.asyncTasks;

import android.os.AsyncTask;

import com.organiser.asyncTasks.asyncTasksCallbacks.SharedTableLoaderCallback;
import com.organiser.services.SharedTableService;
import com.organiser.sharedTable.SharedTable;
import com.organiser.sharedTable.SharedTableDAO;

import java.util.List;

public class SharedTableLoader extends AsyncTask<String,String,List<SharedTable>> {

    private SharedTableService sharedTableService;
    private SharedTableLoaderCallback callback;

    public SharedTableLoader(SharedTableLoaderCallback callback) {
        this.sharedTableService = new SharedTableService(new SharedTableDAO());
        this.callback = callback;
    }

    @Override
    protected List<SharedTable> doInBackground(String... strings){
        try {
            return sharedTableService.getUserAllSharedTables(strings[0]);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<SharedTable> sharedTableList) {
        callback.updateSharedTableList(sharedTableList);
    }
}
