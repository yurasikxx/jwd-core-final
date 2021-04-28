package com.epam.jwd.core_final.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions = true;
    private Collection<Spaceship> spaceships;

    public Spaceship() {
    }

    public Spaceship(Long id, String name, Map<Role, Short> crew, Long flightDistance) {
        super(id, name);
        this.crew = crew;
        this.flightDistance = flightDistance;
    }

    public Spaceship(Boolean isReadyForNextMissions, Collection<Spaceship> spaceships) {
        this.isReadyForNextMissions = isReadyForNextMissions;
        this.spaceships = spaceships;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public Collection<Spaceship> getSpaceships() {
        return spaceships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return Objects.equals(crew, spaceship.crew) && Objects.equals(flightDistance, spaceship.flightDistance) && Objects.equals(isReadyForNextMissions, spaceship.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightDistance, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

}