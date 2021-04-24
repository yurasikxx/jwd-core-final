package com.epam.jwd.core_final.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return isReadyToNextMission == that.isReadyToNextMission && role == that.role && rank == that.rank && Objects.equals(flightMission, that.flightMission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, rank, flightMission, isReadyToNextMission);
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "role=" + role +
                ", rank=" + rank +
                ", flightMission=" + flightMission +
                ", isReadyToNextMission=" + isReadyToNextMission +
                '}';
    }

}