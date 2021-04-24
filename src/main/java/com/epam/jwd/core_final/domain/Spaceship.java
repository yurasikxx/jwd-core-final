package com.epam.jwd.core_final.domain;

import java.util.Map;

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

}