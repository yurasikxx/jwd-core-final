package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
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

    private final String name;
    private final LocalDate start;
    private final LocalDate end;
    private final long distance;
    private final Spaceship assignedSpaceShift;
    private final List<CrewMember> assignedCrew;
    private final MissionResult missionResult;
    private final Planet from;
    private final Planet to;

    public FlightMission(Long id, String name, String name1, LocalDate start, LocalDate end, long distance,
                         Spaceship assignedSpaceShift, List<CrewMember> assignedCrew, MissionResult missionResult,
                         Planet from, Planet to) {
        super(id, name);
        this.name = name1;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public long getDistance() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightMission that = (FlightMission) o;
        return distance == that.distance && Objects.equals(name, that.name) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(assignedSpaceShift, that.assignedSpaceShift) && Objects.equals(assignedCrew, that.assignedCrew) && missionResult == that.missionResult && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end, distance, assignedSpaceShift, assignedCrew, missionResult, from, to);
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "name='" + name + '\'' +
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