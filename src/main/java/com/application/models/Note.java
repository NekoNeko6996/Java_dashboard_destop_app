    package com.application.models;

import java.util.List;


public class Note {
    private int id;
    private String content;
    private String priority;
    private List<String> tag;
    private String icon;
    private String day;
    private int isDone;

    public Note(int id, String content, String priority, List<String> tag, String icon, String day, int isDone) {
        this.id = id;
        this.content = content;
        this.priority = priority;
        this.tag = tag;
        this.icon = icon;
        this.day = day;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
}
