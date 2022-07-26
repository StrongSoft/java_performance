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
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ListAdd {

  int LOOP_COUNT = 1000;
  List<Integer> arrayList;
  List<Integer> linkedList;
  List<Integer> vector;

  @Benchmark
  public void addArrayList() {
    arrayList = new ArrayList<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      arrayList.add(loop);
    }
  }

  @Benchmark
  public void addLinkedList() {
    linkedList = new LinkedList<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      linkedList.add(loop);
    }
  }

  @Benchmark
  public void addVector() {
    vector = new Vector<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      vector.add(loop);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(ListAdd.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }
}
