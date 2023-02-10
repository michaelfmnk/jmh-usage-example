package com.michaelfmnk.jmhdemo.rest;

import com.michaelfmnk.jmhdemo.dto.JokeDto;
import com.michaelfmnk.jmhdemo.model.PGRating;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@State(Scope.Benchmark)
@ExtendWith(SpringExtension.class)
public class JokesControllerTest {

    @Test
    void runBenchmark() throws RunnerException {
        var options = new OptionsBuilder()
                .include("\\." + getClass().getSimpleName())
                .warmupIterations(3)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .forks(1)

                .measurementIterations(5)
                .warmupIterations(5)
                .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() {
        RestAssured.port = 10101;
    }

    @Benchmark
    public void shouldCreateJoke() {
        setup();

        var joke = JokeDto.builder()
                .text("""
                How many programmers does it take to change a light bulb? 
                None, that's a hardware problem
                """)
                .rating(PGRating.G.name())
                .build();

        given()
                .body(joke)
                .contentType(ContentType.JSON)
                .when()
                .post("/jokes")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
