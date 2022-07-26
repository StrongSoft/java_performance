package com.regur.java_performance.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
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
public class SetAdd {

  int LOOP_COUNT = 1000;
  Set<String> set;
  String data = "abcdefghijklmnopqrstuvwxyz";

  @Benchmark
  public void addHashSet() {
    set = new HashSet<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      set.add(data + loop);
    }
  }

  /*@Benchmark
  public void addTreeSet() {
    set = new TreeSet<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      set.add(data + loop);
    }
  }

  @Benchmark
  public void addLinkHashSet() {
    set = new LinkedHashSet<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      set.add(data + loop);
    }
  }*/

  @Benchmark
  public void addHashSetWithInitialSize(){
    set = new HashSet<>(LOOP_COUNT);
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      set.add(data+loop);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(SetAdd.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }
}
