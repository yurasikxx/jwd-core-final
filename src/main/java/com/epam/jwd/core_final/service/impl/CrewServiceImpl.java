package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.exception.UnableToAssignException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.strategy.impl.CrewReadingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private final Collection<CrewMember> crewMembers = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);

    private CrewServiceImpl() {
    }

    private static class CrewServiceImplHolder {
        private final static CrewServiceImpl instance = new CrewServiceImpl();
    }

    public static CrewServiceImpl getInstance() {
        return CrewServiceImplHolder.instance;
    }

    @Override
    public Collection<CrewMember> findAllCrewMembers() {
        return crewMembers;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria) {
        return crewMembers.stream()
                .filter(crewMember -> crewMember.getRank().equals(criteria.getRank()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria) {
        return crewMembers.stream()
                .filter(crewMember -> crewMember.getRole().equals(criteria.getRole()))
                .findFirst();
    }

    @Override
    public void updateCrewMemberDetails(CrewMember crewMember) {
        crewMember = new CrewMember(false, crewMembers);
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws UnableToAssignException {
        if (!crewMember.isReadyToNextMission()) {
            throw new UnableToAssignException(String.format("Crew member %s isn't able to be assigned", crewMember.getName()));
        }
    }

    @Override
    public List<CrewMember> createCrewMember(CrewReadingStrategy crewReadingStrategy) throws EntityExistenceException {
        return crewReadingStrategy.getEntities();
    }

}