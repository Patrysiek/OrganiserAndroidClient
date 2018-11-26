package com.organiser.asyncTasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.organiser.asyncTasks.asyncTasksCallbacks.LoadSharedTableListener;
import com.organiser.services.SharedTableService;
import com.organiser.sharedTable.SharedTableDAO;

public class TableDeleter extends AsyncTask<String,Void,Void>{

    private LoadSharedTableListener callback;
    private boolean ifDropWholeTable;
    public TableDeleter(boolean ifDropWholeTable, LoadSharedTableListener callback) {
        this.callback = callback;
        this.ifDropWholeTable = ifDropWholeTable;
    }

    @Override
    protected Void doInBackground(String... strings) {
        SharedTableService service = new SharedTableService(new SharedTableDAO());
        try {
            service.deleteFromUserSharedTablesTable(strings[0],strings[1]);

            if(ifDropWholeTable){
              service.dropSharedTable(strings[1]);
              service.deleteTableFromAllSharedTablesTable(strings[1]);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.loadSharedTables();
    }
}
