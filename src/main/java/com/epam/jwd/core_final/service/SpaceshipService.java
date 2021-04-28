package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.exception.UnableToAssignException;
import com.epam.jwd.core_final.strategy.impl.SpaceshipReadingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * All its implementations should be a singleton
 * You have to use streamAPI for filtering, mapping, collecting, iterating
 */
public interface SpaceshipService {

    Collection<Spaceship> findAllSpaceships();

    List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria);

    Optional<Spaceship> findSpaceshipByCriteria(SpaceshipCriteria criteria);

    void updateSpaceshipDetails(Spaceship spaceship);

    // todo create custom exception for case, when spaceship is not able to be assigned
    void assignSpaceshipOnMission(Spaceship crewMember) throws UnableToAssignException;

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // spaceship unique criteria - only name!
    List<Spaceship> createSpaceship(SpaceshipReadingStrategy spaceshipReadingStrategy) throws EntityExistenceException;

}
