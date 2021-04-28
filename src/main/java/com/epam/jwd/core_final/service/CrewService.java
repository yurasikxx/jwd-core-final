package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.exception.UnableToAssignException;
import com.epam.jwd.core_final.strategy.impl.CrewReadingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * All its implementations should be a singleton
 * You have to use streamAPI for filtering, mapping, collecting, iterating
 */
public interface CrewService {

    Collection<CrewMember> findAllCrewMembers();

    List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria);

    Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria);

    void updateCrewMemberDetails(CrewMember crewMember);

    // todo create custom exception for case, when crewMember is not able to be assigned
    void assignCrewMemberOnMission(CrewMember crewMember) throws UnableToAssignException;

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // crew member unique criteria - only name!
    List<CrewMember> createCrewMember(CrewReadingStrategy crewReadingStrategy) throws EntityExistenceException;

}