package com.google.sps.data;

public final class Comment {

  private final long id;
  private final String comment;
  private final long time;

  public Comment(long id, String comment, long time) {
    this.id = id;
    this.comment = comment;
    this.time = time;
  }
}