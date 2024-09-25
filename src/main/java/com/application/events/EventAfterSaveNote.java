package com.application.events;

public class EventAfterSaveNote {
  private String message;

  public EventAfterSaveNote(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
