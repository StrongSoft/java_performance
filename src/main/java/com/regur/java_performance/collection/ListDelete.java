package com.regur.java_performance.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ListDelete {

  int LOOP_COUNT = 1000;
  List<Integer> arrayList;
  LinkedList<Integer> linkedList;
  List<Integer> vector;

  @Setup
  public void setUp() {
    arrayList = new ArrayList<>();
    linkedList = new LinkedList<>();
    vector = new Vector<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      arrayList.add(loop);
      linkedList.add(loop);
      vector.add(loop);
    }
  }

  @Benchmark
  public void removeArrayListFromFirst() {
    ArrayList<Integer> tempList = new ArrayList<>(arrayList);
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      tempList.remove(0);
    }
  }

  @Benchmark
  public void removeLinkedListFromFirst(){
    LinkedList<Integer> tempList = new LinkedList<>(linkedList);
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      tempList.remove(0);
    }
  }

  @Benchmark
  public void removeVectorFromFirst() {
    Vector<Integer> tempList = new Vector<>(vector);
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      tempList.remove(0);
    }
  }

  @Benchmark
  public void removeArrayListFromLast() {
    ArrayList<Integer> tempList = new ArrayList<>(arrayList);
    for (int loop = LOOP_COUNT-1; loop >=0; loop--) {
      tempList.remove(loop);
    }
  }

  @Benchmark
  public void removeLinkedListFromLast(){
    LinkedList<Integer> tempList = new LinkedList<>(linkedList);
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      tempList.removeLast();
    }
  }

  @Benchmark
  public void removeVectorFromLast() {
    Vector<Integer> tempList = new Vector<>(vector);
    for (int loop = LOOP_COUNT-1; loop >=0; loop--) {
      tempList.remove(loop);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(ListDelete.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }
}
