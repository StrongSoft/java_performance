package com.regur.java_performance.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CompareTimerJMH {
  @Benchmark
  public DummyData makeObject(){
    HashMap<String, String> map = new HashMap<>(1000000);
    ArrayList<String> list = new ArrayList<>(1000000);
    return new DummyData(map, list);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(CompareTimerJMH.class.getSimpleName())
        .warmupIterations(10)           // 사전 테스트 횟수
        .measurementIterations(10)      // 실제 측정 횟수
        .forks(1)                       //
        .build();
    new Runner(opt).run();                  // 벤치마킹 시작
  }
}
