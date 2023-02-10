package com.michaelfmnk.jmhdemo.rest;

import com.michaelfmnk.jmhdemo.dto.JokeDto;
import com.michaelfmnk.jmhdemo.service.ConvertorService;
import com.michaelfmnk.jmhdemo.service.JokesService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JokesController {
    private final JokesService jokesService;
    private final ConvertorService convertorService;

    @SneakyThrows
    @GetMapping("/sleep")
    public List<JokeDto> sleep() {
        return jokesService.generateJokes().stream()
                .map(JokeDto::of)
                .collect(Collectors.toList());
    }

    @PostMapping("/jokes")
    public JokeDto createJoke(@RequestBody JokeDto jokeDto) {
        var createdJoke = jokesService.createJoke(convertorService.toEntity(jokeDto));
        return JokeDto.of(createdJoke);
    }
}
