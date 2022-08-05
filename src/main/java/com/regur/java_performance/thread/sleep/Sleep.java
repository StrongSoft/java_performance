package com.regur.java_performance.thread.sleep;

public class Sleep extends Thread {

  public static void main(String args[]) {
    Sleep s = new Sleep();
    s.start(); //스레드를 시작한다.
    try {
      int cnt = 0;
      while (cnt < 5) {
        s.join(1000); //1초씩 기다린다.
        cnt++;
        System.out.printf("%d second waited\n", cnt);
      }
      if (s.isAlive()) { //스레드가 살아 있는지 확인하다.
        s.interrupt();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      System.out.println("Somebody stopped me T T");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
