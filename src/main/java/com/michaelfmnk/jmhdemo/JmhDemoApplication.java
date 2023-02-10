package com.michaelfmnk.jmhdemo;

import com.michaelfmnk.jmhdemo.model.Joke;
import com.michaelfmnk.jmhdemo.model.PGRating;
import com.michaelfmnk.jmhdemo.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class JmhDemoApplication {

    @Autowired
    private JokeRepository jokeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JmhDemoApplication.class, args);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStartup() {
        jokeRepository.deleteAll();

        // joke 1
        Joke joke = Joke.builder()
                .isFunny(false)
                .isOffensive(false)
                .rating(PGRating.PG_13)
                .text("I don't trust stairs. They're always up to something.")
                .build();
        jokeRepository.save(joke);

        // joke 2
        joke = Joke.builder()
                .isFunny(false)
                .isOffensive(false)
                .rating(PGRating.PG_13)
                .text("Why couldn't the bicycle stand up by itself? It was two tired.")
                .build();
        jokeRepository.save(joke);


        // joke 3
        joke = Joke.builder()
                .isFunny(true)
                .isOffensive(false)
                .rating(PGRating.R)
                .text("What's brown and sticky? A kebernets.")
                .build();
        jokeRepository.save(joke);

    }


}
