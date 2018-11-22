package com.organiser.SharedTableListView;

import android.content.Intent;
import android.widget.ListView;

import com.organiser.acitvities.MainActivity;
import com.organiser.sharedTable.SharedTable;

public class SharedTableListListener {

    private ListView listView;
    private SharedTableClickListenerCallback listener;
    public SharedTableListListener(SharedTableClickListenerCallback listener,ListView listView){
        this.listener = listener;
        this.listView = listView;
    }

    public void setOnSharedTableLongClickViewListener() {
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            listener.showSharedTableItemDetails((SharedTable)parent.getAdapter().getItem(position));
            return true;

        });
    }
    public void setOnSharedClickViewListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            SharedTable t = (SharedTable) parent.getAdapter().getItem(position);
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("userNameTable",t.getHiddenName());
            view.getContext().startActivity(intent);
        });

    }
}
