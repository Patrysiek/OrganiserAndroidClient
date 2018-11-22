package com.organiser.checkableListView;

import android.view.View;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.List;

public class ListViewUpdater {

    private WeakReference<View> viewWeakReference;
    public ListViewUpdater(View view){
        this.viewWeakReference = new WeakReference<>(view);
    }
    public void updateListView(List<? extends CheckableAndDescriptionable> arrayList, ListView listView){
        View view = viewWeakReference.get();
        CustomAdapter adapter = new CustomAdapter(view.getContext(), arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

}
