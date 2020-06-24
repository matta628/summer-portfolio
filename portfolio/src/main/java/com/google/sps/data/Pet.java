package com.google.sps.data;

public final class Pet {

  private final long id;
  private final String url;
  private final long timestamp;

  public Pet(long id, String url, long timestamp) {
    this.id = id;
    this.url = url;
    this.timestamp = timestamp;
  }
}