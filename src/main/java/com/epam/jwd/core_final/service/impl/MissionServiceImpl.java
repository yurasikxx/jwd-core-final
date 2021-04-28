package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnableToAssignException;
import com.epam.jwd.core_final.service.MissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MissionServiceImpl.class);
    private final Collection<FlightMission> flightMissions = NassaContext
            .getInstance().retrieveBaseEntityList(FlightMission.class);
    private final CrewServiceImpl crewService = CrewServiceImpl.getInstance();
    private final SpaceshipServiceImpl spaceshipService = SpaceshipServiceImpl.getInstance();

    private MissionServiceImpl() {
    }

    private static class MissionServiceImplHolder {
        private final static MissionServiceImpl instance = new MissionServiceImpl();
    }

    public static MissionServiceImpl getInstance() {
        return MissionServiceImplHolder.instance;
    }

    @Override
    public Collection<FlightMission> findAllMissions() {
        return flightMissions;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(FlightMissionCriteria criteria) {
        return flightMissions.stream()
                .filter(flightMission -> flightMission.getDistance().equals(criteria.getDistance()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(FlightMissionCriteria criteria) {
        return flightMissions.stream()
                .filter(flightMission -> flightMission.getMissionName().equals(criteria.getMissionName()))
                .findFirst();
    }

    @Override
    public void updateFlightMissionDetails(FlightMission flightMission) {
        if (flightMission.getMissionResult().equals(MissionResult.FAILED)) {
            crewService.updateCrewMemberDetails(new CrewMember());
            spaceshipService.updateSpaceshipDetails(new Spaceship());
        }

    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        List<Spaceship> availableSpaceships = spaceshipService.findAllSpaceshipsByCriteria(
                new SpaceshipCriteria.SpaceshipCriteriaBuilder().byFlightDistance(flightMission.getDistance()).build())
                .stream()
                .filter(Spaceship::isReadyForNextMissions)
                .collect(Collectors.toList());

        boolean isFound = false;
        List<CrewMember> possibleCrew = new ArrayList<>();

        for (Spaceship availableSpaceship : availableSpaceships) {
            if (!isFound) {
                Map<Role, Short> needCrew = availableSpaceship.getCrew();
                isFound = isFoundCrewMembers(needCrew, possibleCrew);

                if (isFound) {
                    flightMission = new FlightMission(availableSpaceship, flightMissions);

                    try {
                        spaceshipService.assignSpaceshipOnMission(availableSpaceship);
                        flightMission = new FlightMission(possibleCrew, flightMissions);
                    } catch (UnableToAssignException e) {
                        LOGGER.error(e.getMessage());
                    }

                    possibleCrew.forEach(crewMember -> {
                        try {
                            crewService.assignCrewMemberOnMission(crewMember);
                        } catch (UnableToAssignException e) {
                            LOGGER.error(e.getMessage());
                        }
                    });

                    updateFlightMissionDetails(flightMission);
                    flightMissions.add(flightMission);

                    if (flightMission.getMissionResult() == MissionResult.FAILED) {
                        NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class).remove(availableSpaceship);
                        NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class).removeAll(possibleCrew);
                    }
                }
            }
        }

        return flightMission;
    }

    @Override
    public boolean isFoundCrewMembers(Map<Role, Short> needCrew, List<CrewMember> possibleTeam) {
        Set<Map.Entry<Role, Short>> entries = needCrew.entrySet();

        for (Map.Entry<Role, Short> entry : entries) {
            List<CrewMember> crewMembers = crewService
                    .findAllCrewMembersByCriteria(
                            new CrewMemberCriteria.CrewMemberCriteriaBuilder().byRole(entry.getKey()).build()
                    )
                    .stream()
                    .filter(CrewMember::isReadyToNextMission)
                    .collect(Collectors.toList());
            if (crewMembers.size() < entry.getValue()) {
                possibleTeam.clear();
                return false;
            }
            for (int i = 0; i < entry.getValue(); i++) {
                possibleTeam.add(crewMembers.get(i));
            }
        }

        return true;
    }

}