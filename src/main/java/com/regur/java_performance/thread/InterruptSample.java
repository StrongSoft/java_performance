package com.regur.java_performance.thread;

public class InterruptSample {

  public static void main(String[] args) throws InterruptedException {
    InfinitThread infinit = new InfinitThread();
    infinit.start();
    Thread.sleep(2000);
    System.out.println("isInterrupted=" + infinit.isInterrupted());
    infinit.interrupted();
    System.out.println("isInterrupted=" + infinit.isInterrupted());
    //infinit.setFlag(false);
  }

}
