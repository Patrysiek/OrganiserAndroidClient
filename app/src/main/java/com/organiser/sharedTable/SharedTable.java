package com.organiser.sharedTable;

import com.organiser.checkableListView.CheckableAndDescriptionable;

public class SharedTable implements CheckableAndDescriptionable {
    private int ID;
    private String name,hiddenName,password,firstOwner;
    private boolean checked;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDesc() {
        return hiddenName;
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

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getFirstOwner() {
        return firstOwner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
}
