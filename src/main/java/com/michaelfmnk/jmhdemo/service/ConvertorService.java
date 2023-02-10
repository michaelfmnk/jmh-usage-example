package com.michaelfmnk.jmhdemo.service;


import com.michaelfmnk.jmhdemo.dto.JokeDto;
import com.michaelfmnk.jmhdemo.model.Joke;
import com.michaelfmnk.jmhdemo.model.PGRating;
import org.springframework.stereotype.Component;

@Component
public class ConvertorService {
    public Joke toEntity(JokeDto jokeDto) {
        return Joke.builder()
                .text(jokeDto.getText())
                .rating(PGRating.valueOf(jokeDto.getRating()))
                .build();
    }
}
