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
public class ListGet {

  int LOOP_COUNT = 1000;
  List<Integer> arrayList;
  List<Integer> linkedList;
  List<Integer> vector;

  int result = 0;

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
  public void getArrayList() {
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      result = arrayList.get(loop);
    }
  }

  @Benchmark
  public void getLinkedList() {
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      result = linkedList.get(loop);
    }
  }

  @Benchmark
  public void getVector() {
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      result = vector.get(loop);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(ListGet.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }
}
