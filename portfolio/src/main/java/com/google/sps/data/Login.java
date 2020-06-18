package com.google.sps.data;

public final class Login {

  private final String status;
  private final String login;
  private final String logout;

  public Login(String status, String login, String logout) {
    this.status = status;
    this.login = login;
    this.logout = logout;
  }
}