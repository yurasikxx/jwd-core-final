package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {

    private final Role role;
    private final Rank rank;
    private final FlightMission flightMission;
    private boolean isReadyToNextMission = true;

    public CrewMember(Long id, String name, Role role, Rank rank, FlightMission flightMission) {
        super(id, name);
        this.role = role;
        this.rank = rank;
        this.flightMission = flightMission;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public FlightMission getFlightMission() {
        return flightMission;
    }

    public boolean isReadyToNextMission() {
        if (MissionResult.FAILED.equals(flightMission.getMissionResult())) {
            isReadyToNextMission = false;
        }
        return isReadyToNextMission;
    }

}