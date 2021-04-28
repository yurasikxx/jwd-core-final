package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MissionService {

    Collection<FlightMission> findAllMissions();

    List<FlightMission> findAllMissionsByCriteria(FlightMissionCriteria criteria);

    Optional<FlightMission> findMissionByCriteria(FlightMissionCriteria criteria);

    void updateFlightMissionDetails(FlightMission flightMission);

    FlightMission createMission(FlightMission flightMission);

    boolean isFoundCrewMembers(Map<Role, Short> needCrew, List<CrewMember> possibleTeam);


}
