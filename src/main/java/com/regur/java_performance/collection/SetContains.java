package com.regur.java_performance.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
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
public class SetContains {
  int LOOP_COUNT = 1000;
  Set<String> hashSet;
  Set<String> treeSet;
  Set<String> linkedHashSet;

  String data = "abcdefghijklmnopqrstuvwxyz";
  String[] keys;

  @Setup(Level.Trial)
  public void setUp(){
    hashSet = new HashSet<>();
    treeSet = new TreeSet<>();
    linkedHashSet = new LinkedHashSet<>();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      String tempData = data + loop;
      hashSet.add(tempData);
      treeSet.add(tempData);
      linkedHashSet.add(tempData);
    }
    if(keys == null || keys.length != LOOP_COUNT){
      keys = RandomKeyUtil.generateRandomSetKeysSwap(hashSet);
    }
  }

  @Benchmark
  public void containsHashSet(){
    for (String key: keys) {
      hashSet.contains(key);
    }
  }

  @Benchmark
  public void containsTreeSet(){
    for (String key: keys) {
      treeSet.contains(key);
    }
  }

  @Benchmark
  public void containsLinkedHashSet(){
    for (String key: keys) {
      linkedHashSet.contains(key);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(SetContains.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }

}
