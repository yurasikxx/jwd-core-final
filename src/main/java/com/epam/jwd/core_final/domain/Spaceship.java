package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private final Map<Role, Short> crew;
    private final FlightMission flightMission;
    private final long flightDistance;
    private boolean isReadyForNextMissions = true;

    public Spaceship(Long id, String name, Map<Role, Short> crew, FlightMission flightMission, long flightDistance) {
        super(id, name);
        this.crew = crew;
        this.flightMission = flightMission;
        this.flightDistance = flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public FlightMission getFlightMission() {
        return flightMission;
    }

    public long getFlightDistance() {
        return flightDistance;
    }

    public boolean isReadyForNextMissions() {
        if (MissionResult.FAILED.equals(flightMission.getMissionResult())) {
            isReadyForNextMissions = false;
        }
        return isReadyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return flightDistance == spaceship.flightDistance && isReadyForNextMissions == spaceship.isReadyForNextMissions && Objects.equals(crew, spaceship.crew) && Objects.equals(flightMission, spaceship.flightMission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightMission, flightDistance, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "crew=" + crew +
                ", flightMission=" + flightMission +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

}