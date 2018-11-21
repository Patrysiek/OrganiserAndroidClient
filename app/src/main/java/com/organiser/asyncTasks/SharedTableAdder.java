package com.organiser.asyncTasks;

import android.os.AsyncTask;

import com.organiser.asyncTasks.asyncTasksCallbacks.LoadSharedTableListener;
import com.organiser.services.SharedTableService;
import com.organiser.sharedTable.SharedTable;
import com.organiser.sharedTable.SharedTableDAO;

import java.util.List;

public class SharedTableAdder extends AsyncTask<String,String,List<SharedTable>> {

    private SharedTableService sharedTableService;
    private LoadSharedTableListener callback;

    private String userTableName;
    public SharedTableAdder(LoadSharedTableListener callback,String userTableName) {
        this.sharedTableService = new SharedTableService(new SharedTableDAO());
        this.callback = callback;
        this.userTableName = userTableName;
    }

    @Override
    protected List<SharedTable> doInBackground(String... strings){
        try {
            List<SharedTable> tableList= sharedTableService.getParticularSharedTable(strings[0],strings[1]);
            if(tableList!=null){
                SharedTable table = tableList.get(0);
                sharedTableService.insertIntoUserSharedTablesTable(userTableName,table.getName(),table.getHiddenName(),table.getPassword(),table.getFirstOwner());
            }
            return sharedTableService.getParticularSharedTable(strings[0],strings[1]);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    protected void onPostExecute(List<SharedTable> sharedTableList) {
        callback.loadSharedTables();
    }
}
