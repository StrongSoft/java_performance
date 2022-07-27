package com.regur.java_performance.statics;

public class StaticBasicSample2 {

  static String staticVal;

  static {
    staticVal = "Static Value";
    staticVal = StaticBasicSample.staticInt + "";
  }

  static {
    staticVal = "Proformace is important !!!";
  }

  public static void main(String[] args) {
    System.out.println(StaticBasicSample2.staticVal);
  }
}
