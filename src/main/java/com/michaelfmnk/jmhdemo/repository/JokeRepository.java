package com.michaelfmnk.jmhdemo.repository;

import com.michaelfmnk.jmhdemo.model.Joke;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JokeRepository extends MongoRepository<Joke, String> {
}
