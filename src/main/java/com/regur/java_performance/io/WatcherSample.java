package com.regur.java_performance.io;

public class WatcherSample {

  public static void main(String[] args) {
    WatcherThread thread = new WatcherThread("/Users/seungmimlee/Downloads");
    thread.start();
  }
}
