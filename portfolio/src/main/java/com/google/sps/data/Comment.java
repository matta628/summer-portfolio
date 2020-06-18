package com.google.sps.data;

public final class Comment {

  private final long id;
  private final String text;
  private final long time;
  private final String email;

  public Comment(long id, String text, long time, String email) {
    this.id = id;
    this.text = text;
    this.time = time;
    this.email = email;
  }
}