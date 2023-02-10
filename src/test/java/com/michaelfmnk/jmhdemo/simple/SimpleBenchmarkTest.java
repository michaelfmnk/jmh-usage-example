package com.michaelfmnk.jmhdemo.simple;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SimpleBenchmarkTest {

    @Test
    void runBenchmark() throws RunnerException {
        var options = new OptionsBuilder()
                .include("\\." + getClass().getSimpleName())
                .mode(Mode.AverageTime)

                .forks(1)
                .measurementIterations(3)
                .warmupIterations(3)

                .verbosity(VerboseMode.EXTRA)
                .shouldDoGC(false)
                .build();

        new Runner(options).run();
    }

    @Benchmark
    @SneakyThrows
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void simpleBenchmark() {
        int random = new Random().ints(10, 20)
                .findFirst()
                .orElse(0);

        Thread.sleep(random);
    }

    @Benchmark
    public void doNothing() {
    }

    @Benchmark
    @Group("dce")
    @GroupThreads(value = 3)
    public void deadCodeElimination() {
        new Object();
    }

    @Benchmark
    @Group("dce")
    public void avoidingDeadCodeElimination__1(Blackhole blackhole) {
        blackhole.consume(new Object());
    }

    @Benchmark
    @Group("dce")
    public Object avoidingDeadCodeElimination__2() {
        return new Object();
    }

}
