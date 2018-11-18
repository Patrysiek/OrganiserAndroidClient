package com.organiser.sharedTable;

import com.organiser.task.CheckableAndDescriptionable;

public class SharedTable implements CheckableAndDescriptionable {
    private int ID;
    private String name,hiddenName,password;
    private boolean checked;

    public SharedTable(String name, String password, String hiddenName,int id) {
        this.ID = id;
        this.name = name;
        this.hiddenName = hiddenName;
        this.password = password;
    }
    public SharedTable(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return name;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
}
