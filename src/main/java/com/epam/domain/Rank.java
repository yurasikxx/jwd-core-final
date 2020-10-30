package com.epam.domain;

public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        // todo - via java.lang.enum methods!
        return name();
    }

    public static Role resolveRankById(int id) {
        // todo
        return null;
    }



}
