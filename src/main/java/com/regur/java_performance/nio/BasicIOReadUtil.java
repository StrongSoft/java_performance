package com.regur.java_performance.nio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.util.StopWatch;

public class BasicIOReadUtil {

  public static ArrayList readCharStream(String fileName) throws Exception {
    ArrayList<StringBuffer> list = new ArrayList<>();
    FileReader fr = null;
    try {
      fr = new FileReader(fileName);
      int data = 0;
      StringBuffer sb = new StringBuffer();
      while ((data = fr.read()) != -1) {
        if (data == '\n' || data == '\r') {
          list.add(sb);
          sb = new StringBuffer();
        } else {
          sb.append((char) data);
        }
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    } finally {
      if (fr != null) {
        fr.close();
      }
    }
    return list;
  }

  public static String readCharStreamWithBuffer(String fileName) {
    StringBuffer retSB = new StringBuffer();
    FileReader fr = null;
    try {
      fr = new FileReader(fileName);
      int bufferSize = 1024 * 1024;
      char[] readBuffer = new char[bufferSize];
      int resultSize = 0;
      while ((resultSize = fr.read(readBuffer)) != -1) {
        if (resultSize == bufferSize) {
          retSB.append(readBuffer);
        } else {
          for (int loop = 0; loop < resultSize; loop++) {
            retSB.append(readBuffer[loop]);
          }
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return retSB.toString();
  }

  public static ArrayList<String> readBufferedReader(String fileName) throws IOException {
    ArrayList<String> list = new ArrayList<>();
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(fileName));
      String data;
      while ((data = br.readLine()) != null) {
        list.add(data);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw e;
    } finally {
      if (br != null) {
        br.close();
      }
    }
    return list;
  }

  public static void main(String[] args) throws Exception {
    String fieName = "/Users/seungmimlee/Downloads/Profile-20220724T222917.json";
    StopWatch sw = new StopWatch();
    sw.start();
    //ArrayList list1 = BasicIOReadUtil.readCharStream(fieName);
    //System.out.println(list1.size());
/*    String s = BasicIOReadUtil.readCharStreamWithBuffer(fieName);
    System.out.println(sw);
    System.out.println(s.length());*/

    ArrayList list1 = BasicIOReadUtil.readBufferedReader(fieName);
    System.out.println(sw);
    System.out.println(list1.size());
  }

}
