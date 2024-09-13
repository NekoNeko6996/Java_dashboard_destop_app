package com.application.models;

import java.util.List;

public class NoteResponse {

    private String status;
    private List<Note_> notes;

    public NoteResponse(String status, List<Note_> notes) {
        this.status = status;
        this.notes = notes;
    }

    public static class Note_ {

        private final int id;
        private final String content;
        private final String priority;
        private final String tag;
        private final String icon;
        private final String day;
        private final int isDone;

        public Note_(int id, String content, String priority, String tag, String icon, String day, int isDone) {
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

        public String getContent() {
            return content;
        }

        public String getPriority() {
            return priority;
        }

        public String getTag() {
            return tag;
        }

        public String getIcon() {
            return icon;
        }

        public String getDay() {
            return day;
        }

        public int getIsDone() {
            return isDone;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Note_> getNotes() {
        return notes;
    }

    public void setNotes(List<Note_> notes) {
        this.notes = notes;
    }
}
