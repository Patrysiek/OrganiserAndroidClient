package com.organiser.asyncTasks.asyncTasksCallbacks;

import com.organiser.sharedTable.SharedTable;

import java.util.List;

public interface SharedTableLoaderCallback {
    void updateSharedTableList(List<SharedTable> sharedTableList);
}
