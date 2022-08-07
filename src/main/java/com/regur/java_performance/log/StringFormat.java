package com.regur.java_performance.log;

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
public class StringFormat {

  int LOOP_COUNT = 1000;
  String a = "aaa", b = "bbb", c = "ccc";
  long d = 1, e = 2, f = 3;
  String data;

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(StringFormat.class.getSimpleName())
        .warmupIterations(3)           // 사전 테스트 횟수
        .measurementIterations(3)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(options).run();
  }

  @Benchmark
  public void timeStringADD() {
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      data = a + " " + b + " " + c + " " + d + " " + e + " " + f;
    }
  }

  @Benchmark
  public void timeFormat() {
    for (int loop = 0; loop < LOOP_COUNT; loop++) {
      data = String.format("%s %s %s %d %d %d", a, b, c, d, e, f);
    }
  }
}
