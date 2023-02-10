package com.michaelfmnk.jmhdemo.simple;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class BenchmarkWithState2Test {

    @State(Scope.Benchmark)
    public static class SomeState {
        public int a;
        public int b;
        public int sum;


        @Setup
        public void setup() {
            a = RandomUtils.nextInt(10, 100);
            b = RandomUtils.nextInt(10, 100);
        }

        @TearDown
        public void tearDown() {
            System.err.println(sum);
        }
    }

    @Test
    void runBenchmark() throws RunnerException {
        var options = new OptionsBuilder()
                .include("\\." + getClass().getSimpleName())
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .measurementIterations(3)
                .warmupIterations(3)
                .build();

        new Runner(options).run();
    }


    @Benchmark
    public void testMethod(SomeState state, Blackhole blackhole) {
        state.sum = state.a + state.b;

        blackhole.consume(state);
    }

}
