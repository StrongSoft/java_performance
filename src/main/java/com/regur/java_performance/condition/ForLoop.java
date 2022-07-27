package com.regur.java_performance.condition;

import java.util.ArrayList;
import java.util.List;
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
public class ForLoop {

  int LOOP_COUNT = 100000;
  List<Integer> list;
  int current;

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(ForLoop.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }

  @Setup
  public void setUp() {
    list = new ArrayList<>(LOOP_COUNT);
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      list.add(loop);
    }
  }

  @Benchmark
  public void traditionForLoop() {
    int listSize = list.size();
    for (int loop = 0; loop < listSize; loop++) {
      resultProcess(list.get(loop));
    }
  }

  @Benchmark
  public void traditionSizeForLoop() {
    for (int loop = 0; loop < list.size(); loop++) {
      resultProcess(list.get(loop));
    }
  }

  @Benchmark
  public void traditionEachLoop() {
    for (Integer integer : list) {
      resultProcess(integer);
    }
  }

  private void resultProcess(int result) {
    current = result;
  }
}
