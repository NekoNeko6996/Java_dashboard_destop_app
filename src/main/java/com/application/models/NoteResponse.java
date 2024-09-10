package com.application.models;

import java.util.List;


public class NoteResponse {
    private String status;
    private List<Note> notes;

    public NoteResponse(String status, List<Note> notes) {
        this.status = status;
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
