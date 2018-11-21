package com.organiser.checkableListView;

public interface CheckableAndDescriptionable {
    String getName();
    boolean isChecked();
    void setChecked(boolean checked);
    int getID();
    String getDesc();
}
