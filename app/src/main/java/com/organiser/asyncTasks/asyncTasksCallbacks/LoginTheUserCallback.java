package com.organiser.asyncTasks.asyncTasksCallbacks;

public interface LoginTheUserCallback {
    void startTaskChoiceActivity(String userData);
    void invalidLoginOrPassword();
    void serverError();
}
