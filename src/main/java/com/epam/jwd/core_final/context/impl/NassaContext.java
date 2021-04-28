package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.impl.CrewReadingStrategy;
import com.epam.jwd.core_final.strategy.impl.SpacemapReadingStrategy;
import com.epam.jwd.core_final.strategy.impl.SpaceshipReadingStrategy;
import com.epam.jwd.core_final.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    private NassaContext() {
    }

    private static class NassaContextHolder {
        private final static NassaContext instance = new NassaContext();
    }

    public static NassaContext getInstance() {
        return NassaContextHolder.instance;
    }

    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();
    private final Collection<Planet> planetMap = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(NassaContext.class);
    private final static String FILE_DIRECTORY_PATH = "." + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + PropertyReader.getInstance().readProperties().getInputRootDir() + File.separator;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(CrewMember.class.getName())) {
            return (Collection<T>) crewMembers;
        } else if (tClass.getName().equals(Spaceship.class.getName())) {
            return (Collection<T>) spaceships;
        } else if (tClass.getName().equals(FlightMission.class.getName())) {
            return (Collection<T>) flightMissions;
        } else if (tClass.getName().equals(Planet.class.getName())) {
            return (Collection<T>) planetMap;
        } else {
            throw new IllegalArgumentException("There's no such entity list");
        }
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        SpaceshipServiceImpl spaceshipService = SpaceshipServiceImpl.getInstance();
        CrewReadingStrategy crewReadingStrategy = CrewReadingStrategy.getInstance();
        SpaceshipReadingStrategy spaceshipReadingStrategy = SpaceshipReadingStrategy.getInstance();
        SpacemapServiceImpl spacemapService = SpacemapServiceImpl.getInstance();
        SpacemapReadingStrategy spacemapReadingStrategy = SpacemapReadingStrategy.getInstance();

        try {
            crewReadingStrategy.readFromFile(FILE_DIRECTORY_PATH +
                    PropertyReader.getInstance().readProperties().getCrewFileName());
            spaceshipReadingStrategy.readFromFile(FILE_DIRECTORY_PATH +
                    PropertyReader.getInstance().readProperties().getSpaceshipsFileName());
            spacemapReadingStrategy.readFromFile(FILE_DIRECTORY_PATH +
                    PropertyReader.getInstance().readProperties().getSpacemapsFileName());
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            crewMembers.addAll(crewService.createCrewMember(crewReadingStrategy));
            spaceships.addAll(spaceshipService.createSpaceship(spaceshipReadingStrategy));
            planetMap.addAll(spacemapService.createPlanet(spacemapReadingStrategy));
        } catch (EntityExistenceException e) {
            LOGGER.error(e.getMessage());
        }
    }

}