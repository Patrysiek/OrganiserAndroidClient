package com.organiser.acitvities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.organiser.R;
import com.organiser.asyncTasks.SharedTableAdder;
import com.organiser.asyncTasks.SharedTableCreator;
import com.organiser.asyncTasks.SharedTableLoader;
import com.organiser.asyncTasks.asyncTasksCallbacks.LoadSharedTableListener;
import com.organiser.asyncTasks.asyncTasksCallbacks.SharedTableLoaderCallback;
import com.organiser.SharedTableListView.SharedTableClickListenerCallback;
import com.organiser.SharedTableListView.SharedTableListListener;
import com.organiser.dialogs.AddSharedTableDialog;
import com.organiser.dialogs.CreateSharedTableDialog;
import com.organiser.dialogs.SharedTableDetailsDialog;
import com.organiser.dialogs.dialogsCallbacks.AddSharedTableDialogCallback;
import com.organiser.dialogs.dialogsCallbacks.CreateSharedTableDialogCallback;
import com.organiser.sharedTable.SharedTable;

import java.util.List;


public class SharedTablesActivity extends AppCompatActivity
        implements SharedTableLoaderCallback,AddSharedTableDialogCallback,CreateSharedTableDialogCallback,LoadSharedTableListener,SharedTableClickListenerCallback {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_tables);
        listView = findViewById(R.id.shared_task_list_view);
        loadSharedTables();
    }

    @Override
    public void loadSharedTables() {
        new SharedTableLoader(this).execute(getIntent().getStringExtra("userName")+"sharedtable");
    }
    public void addSharedTableFromServer(View view){
        new AddSharedTableDialog().show(getSupportFragmentManager(),"add_table_dialog");
    }
    public void createSharedTable(View view){
        new CreateSharedTableDialog().show(getSupportFragmentManager(),"create_table_dialog");
    }
    @Override
    public void updateSharedTableList(List<SharedTable> sharedTableList) {
        ArrayAdapter<SharedTable> adapter = new ArrayAdapter<>(this,R.layout.shared_table_listview_layout,R.id.status_item,sharedTableList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        SharedTableListListener listener = new SharedTableListListener(this,listView);
        listener.setOnSharedTableLongClickViewListener();
        listener.setOnSharedClickViewListener();
    }
    @Override
    public void addSharedTableDialogClick(String hiddenName, String password) {
        new SharedTableAdder(this,getIntent().getStringExtra("userName") + "sharedtable").execute(hiddenName,password);
    }

    @Override
    public void createSharedTableDialogClick(String tableName, String password) {
        new SharedTableCreator(this).execute(tableName,password,getIntent().getStringExtra("userName"));
    }

    @Override
    public void showSharedTableItemDetails(SharedTable table) {
        SharedTableDetailsDialog dialog = new SharedTableDetailsDialog();
        dialog.setTable(table);
        dialog.show(getSupportFragmentManager(),"Details");
    }
}
