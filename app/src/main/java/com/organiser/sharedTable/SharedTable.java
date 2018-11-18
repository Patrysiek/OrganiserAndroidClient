package com.organiser.sharedTable;

import com.organiser.task.CheckableAndDescriptionable;

public class SharedTable implements CheckableAndDescriptionable {
    private int ID;
    private String tableName,hiddenName,password;
    private boolean checked;

    public SharedTable(int ID, String tableName, String hiddenName, String password) {
        this.ID = ID;
        this.tableName = tableName;
        this.hiddenName = hiddenName;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHiddenName() {
        return hiddenName;
    }

    public void setHiddenName(String hiddenName) {
        this.hiddenName = hiddenName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getDescription() {
        return tableName;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
}
