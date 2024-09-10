package com.application.models;


public class Note {
    private int id;
    private String content;
    private String type;
    private String tag;
    private String icon;
    private int isDone;

    public Note(int id, String content, String type, String tag, String icon, int isDone) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.tag = tag;
        this.icon = icon;
        this.isDone = isDone;
    }

    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int isIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
