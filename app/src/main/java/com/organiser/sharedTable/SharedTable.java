package com.organiser.sharedTable;

public class SharedTable{
    private int ID;
    private String name,hiddenName,password,firstOwner;
    private boolean checked;

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

    public String getFirstOwner() {
        return firstOwner;
    }

    public void setFirstOwner(String firstOwner) {
        this.firstOwner = firstOwner;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription(){
        StringBuilder builder = new StringBuilder();
        builder.append("Hidden name: ").append(hiddenName).append("\n")
                .append("Name: ").append(name).append("\n")
                .append("Password: ").append(password).append("\n")
                .append("First Owner: ").append(firstOwner);
        return builder.toString();
    }
}
