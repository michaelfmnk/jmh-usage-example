package com.michaelfmnk.jmhdemo.service;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;


@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JokesServiceTest {

    private static JokesService jokesService;

    @Autowired
    public void setRepository(JokesService jokesService) {
        JokesServiceTest.jokesService = jokesService;
    }

    @Test
    void runBenchmarks() throws Exception {
        var options = new OptionsBuilder()
                // set the class name regex for benchmarks to search for to the current class
                .include("\\." + this.getClass().getSimpleName() + "\\.")
                .warmupIterations(3)
                .measurementIterations(3)
                // do not use forking or the benchmark methods will not see references stored within its class
                .forks(0)
                // do not use multiple threads
                .threads(1)
                // add stack profiler
                .addProfiler("stack", "lines=3")

                .shouldDoGC(true)
                .shouldFailOnError(true)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void getJokes() {
        jokesService.generateJokes();
    }

}
