package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.FlightMission;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    private final String missionName;
    private final Long distance;

    protected FlightMissionCriteria(final FlightMissionCriteriaBuilder builder) {
        super();
        this.missionName = builder.missionName;
        this.distance = builder.distance;
    }

    public String getMissionName() {
        return missionName;
    }

    public Long getDistance() {
        return distance;
    }

    public static class FlightMissionCriteriaBuilder extends Criteria.Builder<FlightMissionCriteria> {
        private String missionName;
        private Long distance;

        public FlightMissionCriteriaBuilder byName(String name) {
            this.missionName = name;
            return this;
        }

        public FlightMissionCriteriaBuilder byDistance(Long distance) {
            this.distance = distance;
            return this;
        }

        @Override
        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(this);
        }
    }

}