package com.organiser.acitvities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.organiser.R;
import com.organiser.asyncTasks.SharedTableLoader;
import com.organiser.asyncTasksCallbacks.SharedTableLoaderCallback;
import com.organiser.sharedTable.SharedTable;
import com.organiser.taskList.ListViewUpdater;

import java.util.List;


public class SharedTablesActivity extends Activity implements SharedTableLoaderCallback {

    private ListView listView;
    private List<SharedTable> sharedTableList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_tables);
        listView = findViewById(R.id.shared_task_list_view);
        loadSharedTables();
        new ListViewUpdater(findViewById(android.R.id.content)).updateListView(sharedTableList,listView);
    }

    private void loadSharedTables() {
        new SharedTableLoader(this).execute(getIntent().getStringExtra("userName") + "sharedtable");
    }

    public void addSharedTableFromServer(View view){

    }


    @Override
    public void initSharedTableList(List<SharedTable> sharedTableList) {
        this.sharedTableList = sharedTableList;
    }
}
