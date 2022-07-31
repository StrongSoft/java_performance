package com.regur.java_performance.reflect.clas;

import java.io.IOException;

public class DemoClass {

  public String publicField;
  protected String protectedField;
  String field;
  private String privateField;

  public DemoClass() {

  }

  public DemoClass(String arg) {
  }

  public void publicMethod() throws IOException {
  }

  public String publicMethod(String s, int i) {
    return "s=" + s + " i" + i;
  }

  protected void protectedMethod() {

  }

  private void privateMethod() {

  }

  void method() {
  }

  public String publicRetMethod() {
    return null;
  }

  public InnerClass getInstance() {
    return new InnerClass();
  }

  public class InnerClass {

  }
}
