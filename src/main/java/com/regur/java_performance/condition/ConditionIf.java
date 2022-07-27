package com.regur.java_performance.condition;

import com.regur.java_performance.collection.SetIterate;
import java.util.Random;
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
public class ConditionIf {

  int LOOP_COUNT = 1000;

  @Benchmark
  public void randomOnly() {
    Random random = new Random();
    int data = 1000 + random.nextInt();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      resultProcess("dummy");
    }
  }

  @Benchmark
  public void if10() {
    Random random = new Random();
    String result = null;
    int data = 1000 + random.nextInt();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      if (data < 50) {
        result = "50";
      } else if (data < 150) {
        result = "150";
      } else if (data < 250) {
        result = "250";
      } else if (data < 350) {
        result = "350";
      } else if (data < 450) {
        result = "450";
      } else if (data < 550) {
        result = "550";
      } else if (data < 650) {
        result = "650";
      } else if (data < 750) {
        result = "750";
      } else if (data < 850) {
        result = "850";
      } else if (data < 950) {
        result = "950";
      } else {
        result = "over";
      }
      resultProcess(result);
    }
  }

  @Benchmark
  public void if100() {
    Random random = new Random();
    String result = null;
    int data = 10000 + random.nextInt();
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      if (data < 50) {
        result = "50";
      } else if (data < 150) {
        result = "150";
      } else if (data < 250) {
        result = "250";
      } else if (data < 350) {
        result = "350";
      } else if (data < 450) {
        result = "450";
      } else if (data < 550) {
        result = "550";
      } else if (data < 650) {
        result = "650";
      } else if (data < 750) {
        result = "750";
      } else if (data < 850) {
        result = "850";
      } else if (data < 950) {
        result = "950";
      } else {
        result = "over";
      }
      resultProcess(result);
    }
  }

  String current;

  private void resultProcess(String result) {
    current = result;
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(ConditionIf.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }
}
