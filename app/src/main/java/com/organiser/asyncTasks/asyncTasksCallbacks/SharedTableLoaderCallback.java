package com.organiser.asyncTasksCallbacks;

import com.organiser.sharedTable.SharedTable;

import java.util.List;

public interface SharedTableLoaderCallback {
    void updateSharedTableList(List<SharedTable> sharedTableList);
}
