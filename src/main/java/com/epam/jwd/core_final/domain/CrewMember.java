package com.epam.jwd.core_final.domain;

import java.util.Collection;
import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {

    private Role role;
    private Rank rank;
    private Boolean isReadyToNextMission = true;
    private Collection<CrewMember> crewMembers;

    public CrewMember() {
    }

    public CrewMember(Long id, String name, Role role, Rank rank) {
        super(id, name);
        this.role = role;
        this.rank = rank;
    }

    public CrewMember(Boolean isReadyToNextMission, Collection<CrewMember> crewMembers) {
        this.isReadyToNextMission = isReadyToNextMission;
        this.crewMembers = crewMembers;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean isReadyToNextMission() {
        return isReadyToNextMission;
    }

    public Collection<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return role == that.role && rank == that.rank && Objects.equals(isReadyToNextMission, that.isReadyToNextMission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, rank, isReadyToNextMission);
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "role=" + role +
                ", rank=" + rank +
                ", isReadyToNextMission=" + isReadyToNextMission +
                '}';
    }

}