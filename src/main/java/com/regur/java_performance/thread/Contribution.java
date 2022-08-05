package com.regur.java_performance.thread;

public class Contribution {

  private static int amount = 0;

  public static synchronized void donate() {
    amount++;
  }

  public int getTotal() {
    return amount;
  }

}
