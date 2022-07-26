package com.regur.java_performance.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
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
public class MapGet {
  int LOOP_COUNT = 1000;
  Map<Integer, String> hashMap;
  Map<Integer, String> hashTable;
  Map<Integer, String> treeMap;
  Map<Integer, String> linkedHashMap;
  int keys[];

  @Setup
  public void setUp(){
    if(keys == null || keys.length != LOOP_COUNT) {
      hashMap = new HashMap<>();
      hashTable = new Hashtable<>();
      treeMap = new TreeMap<>();
      linkedHashMap = new LinkedHashMap<>();
      String data = "abcdefghijklmnopqrstuvwxyz";
      for (int loop = 0; loop < LOOP_COUNT; loop++) {
        String tempData = data + loop;
        hashMap.put(loop, tempData);
        hashTable.put(loop, tempData);
        treeMap.put(loop, tempData);
        linkedHashMap.put(loop, tempData);
      }

      keys = RandomKeyUtil.generateRandomSetKeysSwap(LOOP_COUNT);
    }
  }

  @Benchmark
  public void getSeqHashMap(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      hashMap.get(loop);
    }
  }

  @Benchmark
  public void getRandomHashMap(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      hashMap.get(keys[loop]);
    }
  }

  @Benchmark
  public void getSeqHashTable(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      hashTable.get(loop);
    }
  }

  @Benchmark
  public void getRandomHashTable(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      hashTable.get(keys[loop]);
    }
  }

  @Benchmark
  public void getSeqTreeMap(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      treeMap.get(loop);
    }
  }

  @Benchmark
  public void getRandomTreeMap(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      treeMap.get(keys[loop]);
    }
  }

  @Benchmark
  public void getSeqLinkedHashMap(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      linkedHashMap.get(loop);
    }
  }

  @Benchmark
  public void getRandomLinkedHashMap(){
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      linkedHashMap.get(keys[loop]);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(MapGet.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }
}
