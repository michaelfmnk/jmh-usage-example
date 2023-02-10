package com.michaelfmnk.jmhdemo.service;

import com.michaelfmnk.jmhdemo.model.Joke;
import com.michaelfmnk.jmhdemo.model.PGRating;
import com.michaelfmnk.jmhdemo.repository.JokeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class JokesService {
    private final JokeRepository jokeRepository;
    private final SecretService secretService;


    @SneakyThrows
    public List<Joke> generateJokes() {
        List<Joke> allJokes = jokeRepository.findAll();
        allJokes.forEach(this::censor);
        return allJokes;
    }

    private void censor(Joke joke) {
        if (joke.getRating().getLevel() >= PGRating.R.getLevel()) {
            joke.setText(callServiceToReplace(joke.getText()));
        }
    }

    @SneakyThrows
    private String callServiceToReplace(String text) {
        CompletableFuture.runAsync(secretService::notifyAdmins);
        return text.replace("damn", "wow");
    }

    public Joke createJoke(Joke joke) {
        joke.setFunny(true);
        joke.setOffensive(false);
        return jokeRepository.save(joke);
    }
}
