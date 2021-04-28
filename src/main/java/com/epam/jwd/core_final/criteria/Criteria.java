package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    private Long id;
    private String name;

    public Criteria() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static abstract class Builder<T> {
        public Builder<T> byId(Long id) {
            return this;
        }

        public Builder<T> byName(String name) {
            return this;
        }

        public abstract T build();
    }

}