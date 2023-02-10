package com.michaelfmnk.jmhdemo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PGRating {
    G(1), PG(2), PG_13(3), R(4), NC_17(5);

    @Getter
    private final int level;
}
