package com.lucascorrea.tasklist;

import androidx.annotation.NonNull;

public class Task {

    private int id;
    private String title, priority, executed;

    public Task() {

    }

    public Task(String title, String priority, String executed) {
        this.title = title;
        this.priority = priority;
        this.executed = executed;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " / " + priority + " - Status: " + executed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
    }

}
