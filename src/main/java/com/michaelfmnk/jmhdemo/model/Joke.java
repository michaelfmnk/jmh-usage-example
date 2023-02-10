package com.michaelfmnk.jmhdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("jokes")
@NoArgsConstructor
@AllArgsConstructor
public class Joke {
    @Id
    private String id;
    private boolean isFunny;
    private boolean isOffensive;
    private String text;
    private PGRating rating;
}
