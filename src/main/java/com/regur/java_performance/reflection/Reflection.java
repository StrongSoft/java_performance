package com.regur.java_performance.reflection;

import java.math.BigDecimal;
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
public class Reflection {

  int LOOP_COUNT = 100;
  String result;

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(Reflection.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }

  @Benchmark
  public void withEquals() {
    Object src = new BigDecimal("6");
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      if (src.getClass().getCanonicalName().equals("java.math.BigDecimal")) {
        result = "BigDecimal";
      }
    }
  }

  @Benchmark
  public void withInstanceof() {
    Object src = new BigDecimal("6");
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      if (src instanceof BigDecimal) {
        result = "BigDecimal";
      }
    }
  }
}
