package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.exception.UnableToAssignException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.strategy.impl.SpaceshipReadingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private final Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);

    private SpaceshipServiceImpl() {
    }

    private static class SpaceshipServiceImplHolder {
        private final static SpaceshipServiceImpl instance = new SpaceshipServiceImpl();
    }

    public static SpaceshipServiceImpl getInstance() {
        return SpaceshipServiceImplHolder.instance;
    }

    @Override
    public Collection<Spaceship> findAllSpaceships() {
        return spaceships;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {
        return spaceships.stream()
                .filter(spaceship -> spaceship.getFlightDistance().equals(criteria.getFlightDistance()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(SpaceshipCriteria criteria) {
        return spaceships.stream()
                .filter(spaceship -> spaceship.getFlightDistance().equals(criteria.getFlightDistance()))
                .findFirst();
    }

    @Override
    public void updateSpaceshipDetails(Spaceship spaceship) {
        new Spaceship(false, spaceships);
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws UnableToAssignException {
        if (!crewMember.isReadyForNextMissions()) {
            throw new UnableToAssignException(String.format("Spaceship %s isn't able to be assigned on mission",
                    crewMember.getName()));
        }
    }

    @Override
    public List<Spaceship> createSpaceship(SpaceshipReadingStrategy spaceshipReadingStrategy) throws EntityExistenceException {
        return spaceshipReadingStrategy.getEntities();
    }

}
