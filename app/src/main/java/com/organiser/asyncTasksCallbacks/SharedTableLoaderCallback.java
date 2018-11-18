package com.organiser.asyncTasksCallbacks;

import com.organiser.sharedTable.SharedTable;

import java.util.List;

public interface SharedTableLoaderCallback {
    void initSharedTableList(List<SharedTable> sharedTableList);
}
