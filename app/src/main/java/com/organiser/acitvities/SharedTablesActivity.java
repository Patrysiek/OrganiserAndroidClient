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
import com.organiser.asyncTasks.TableDeleter;
import com.organiser.asyncTasks.asyncTasksCallbacks.LoadSharedTableListener;
import com.organiser.asyncTasks.asyncTasksCallbacks.SharedTableLoaderCallback;
import com.organiser.SharedTableListView.SharedTableClickListenerCallback;
import com.organiser.SharedTableListView.SharedTableListListener;
import com.organiser.dialogs.AddSharedTableDialog;
import com.organiser.dialogs.CreateSharedTableDialog;
import com.organiser.dialogs.SharedTableDeleteDialog;
import com.organiser.dialogs.SharedTableDetailsDialog;
import com.organiser.dialogs.dialogsCallbacks.AddSharedTableDialogCallback;
import com.organiser.dialogs.dialogsCallbacks.CreateSharedTableDialogCallback;
import com.organiser.dialogs.dialogsCallbacks.SharedTableDeleteDialogCallback;
import com.organiser.dialogs.dialogsCallbacks.SharedTableDetailsDialogCallback;
import com.organiser.sharedTable.SharedTable;

import java.util.List;


public class SharedTablesActivity extends AppCompatActivity
        implements SharedTableLoaderCallback,AddSharedTableDialogCallback,CreateSharedTableDialogCallback,LoadSharedTableListener,SharedTableClickListenerCallback,SharedTableDetailsDialogCallback,SharedTableDeleteDialogCallback {

    private ListView listView;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_tables);


        username = getIntent().getStringExtra("userName");
        listView = findViewById(R.id.shared_task_list_view);
        loadSharedTables();
    }


    public void addSharedTableFromServer(View view){
        new AddSharedTableDialog().show(getSupportFragmentManager(),"add_table_dialog");
    }
    public void createSharedTable(View view){
        new CreateSharedTableDialog().show(getSupportFragmentManager(),"create_table_dialog");
    }
    public void refreshView(View view){
        loadSharedTables();
    }
    @Override
    public void loadSharedTables() {
        new SharedTableLoader(this).execute(username+"sharedtable");
    }
    @Override
    public void updateSharedTableList(List<SharedTable> sharedTableList) {
        if(sharedTableList!=null) {
            ArrayAdapter<SharedTable> adapter = new ArrayAdapter<>(this, R.layout.shared_table_listview_layout, R.id.status_item, sharedTableList);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            SharedTableListListener listener = new SharedTableListListener(this, listView);
            listener.setOnSharedTableLongClickViewListener();
            listener.setOnSharedClickViewListener();
        }
    }
    @Override
    public void addSharedTableDialogClick(String hiddenName, String password) {
        new SharedTableAdder(this,username + "sharedtable").execute(hiddenName,password);
    }

    @Override
    public void createSharedTableDialogClick(String tableName, String password) {
        new SharedTableCreator(this).execute(tableName,password,username);
    }

    @Override
    public void showSharedTableItemDetails(SharedTable table) {
        SharedTableDetailsDialog dialog = new SharedTableDetailsDialog();
        dialog.setTable(table);
        dialog.show(getSupportFragmentManager(),"Details");
    }

    @Override
    public void onDetailsDeleteClick(String hiddenName,String firstOwner) {
        SharedTableDeleteDialog dialog = new SharedTableDeleteDialog();
        dialog.setIfFirstOwner(firstOwner.equals(username));
        dialog.setHiddenName(hiddenName);
        dialog.show(getSupportFragmentManager(),"deleteDialog");
    }

    @Override
    public void deleteTable(String hiddenName,boolean ifDropWholeTable) {
       new TableDeleter(ifDropWholeTable,this).execute(username+"sharedtable",hiddenName);

    }
}
