package com.michaelfmnk.jmhdemo.dto;

import com.michaelfmnk.jmhdemo.model.Joke;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JokeDto {
    private String text;
    private String rating;

    public static JokeDto of(Joke joke) {
        return JokeDto.builder()
                .rating(joke.getRating().name())
                .text(joke.getText())
                .build();
    }
}
