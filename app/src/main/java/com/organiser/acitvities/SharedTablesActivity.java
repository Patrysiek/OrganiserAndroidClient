package com.organiser.acitvities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.organiser.R;
import com.organiser.asyncTasks.SharedTableAdder;
import com.organiser.asyncTasks.SharedTableLoader;
import com.organiser.asyncTasksCallbacks.SharedTableLoaderCallback;
import com.organiser.dialogs.AddSharedTableDialog;
import com.organiser.dialogs.AddSharedTableDialogCallback;
import com.organiser.sharedTable.SharedTable;
import com.organiser.taskList.ListViewUpdater;

import java.util.List;


public class SharedTablesActivity extends AppCompatActivity implements SharedTableLoaderCallback,AddSharedTableDialogCallback {

    private ListView listView;
    private List<SharedTable> sharedTableList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_tables);
        listView = findViewById(R.id.shared_task_list_view);
        loadSharedTables();

    }

    private void loadSharedTables() {
        new SharedTableLoader(this).execute(getIntent().getStringExtra("userName") + "sharedtable");
    }

    public void addSharedTableFromServer(View view){
        new AddSharedTableDialog().show(getSupportFragmentManager(),"add_table_dialog");
    }


    @Override
    public void initSharedTableList(List<SharedTable> sharedTableList) {
        this.sharedTableList = sharedTableList;
        new ListViewUpdater(findViewById(android.R.id.content)).updateListView(sharedTableList,listView);
    }

    @Override
    public void addSharedTableDialogClick(String hiddenName, String password) {
        new SharedTableAdder(this,getIntent().getStringExtra("userName") + "sharedtable").execute(hiddenName,password);

    }
}
