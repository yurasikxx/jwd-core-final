package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {

    private String missionName;
    private LocalDate start;
    private LocalDate end;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private Planet from;
    private Planet to;
    private Collection<FlightMission> flightMissions;

    public FlightMission(Long id, String missionName, LocalDate start, LocalDate end, Long distance, Spaceship assignedSpaceShift, List<CrewMember> assignedCrew, MissionResult missionResult, Planet from, Planet to, Collection<FlightMission> flightMissions) {
        super(id);
        this.missionName = missionName;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
        this.from = from;
        this.to = to;
        this.flightMissions = flightMissions;
    }


    public FlightMission(Long id, String missionName, LocalDate start, LocalDate end, Long distance,
                         MissionResult missionResult, Planet from, Planet to) {
        super(id);
        this.missionName = missionName;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.missionResult = missionResult;
        this.from = from;
        this.to = to;
    }

    public FlightMission(Long id, String missionName, LocalDate start, LocalDate end, Long distance) {
        super(id);
        this.missionName = missionName;
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public FlightMission(Spaceship assignedSpaceShift, Collection<FlightMission> flightMissions) {
        this.assignedSpaceShift = assignedSpaceShift;
        this.flightMissions = flightMissions;
    }

    public FlightMission(List<CrewMember> assignedCrew, Collection<FlightMission> flightMissions) {
        this.assignedCrew = assignedCrew;
        this.flightMissions = flightMissions;
    }

    public String getMissionName() {
        return missionName;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public Planet getFrom() {
        return from;
    }

    public Planet getTo() {
        return to;
    }

    public Collection<FlightMission> getFlightMissions() {
        return flightMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMission that = (FlightMission) o;
        return Objects.equals(missionName, that.missionName) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(distance, that.distance) && Objects.equals(assignedSpaceShift, that.assignedSpaceShift) && Objects.equals(assignedCrew, that.assignedCrew) && missionResult == that.missionResult && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionName, start, end, distance, assignedSpaceShift, assignedCrew, missionResult, from, to);
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "missionName='" + missionName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                ", assignedSpaceShift=" + assignedSpaceShift +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

}