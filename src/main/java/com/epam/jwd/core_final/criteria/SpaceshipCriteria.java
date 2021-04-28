package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final Long flightDistance;

    protected SpaceshipCriteria(final SpaceshipCriteria.SpaceshipCriteriaBuilder builder) {
        super();
        this.flightDistance = builder.flightDistance;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public static class SpaceshipCriteriaBuilder extends Criteria.Builder<SpaceshipCriteria> {
        private Long flightDistance;

        public SpaceshipCriteriaBuilder byFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        @Override
        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }
    }

}