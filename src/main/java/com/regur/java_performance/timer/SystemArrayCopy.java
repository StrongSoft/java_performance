package com.regur.java_performance.timer;

public class SystemArrayCopy {

  public static void main(String[] args) {
    String[] arr = new String[]{"AAA","BBB","CCC","DDD","EEE"};

    String[] copiedArr = new String[3];
    System.arraycopy(arr, 2, copiedArr, 1, 2);

    for (String value: copiedArr) {
      System.out.println(value);
    }
  }
}
