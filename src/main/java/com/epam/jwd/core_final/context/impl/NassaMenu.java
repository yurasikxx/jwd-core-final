package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.PropertyReader;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class NassaMenu implements ApplicationMenu {

    private NassaMenu() {
    }

    private static class NassaMenuHolder {
        private final static NassaMenu instance = new NassaMenu();
    }

    public static NassaMenu getInstance() {
        return NassaMenuHolder.instance;
    }

    private final Scanner scanner = new Scanner(System.in);
    private final CrewServiceImpl crewService = CrewServiceImpl.getInstance();
    private final SpaceshipServiceImpl spaceshipService = SpaceshipServiceImpl.getInstance();
    private final SpacemapServiceImpl spacemapService = SpacemapServiceImpl.getInstance();
    private final MissionServiceImpl missionService = MissionServiceImpl.getInstance();
    private final static String FILE_DIRECTORY_PATH = "." + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + PropertyReader.getInstance().readProperties().getOutputRootDir() + File.separator +
            PropertyReader.getInstance().readProperties().getMissionsFileName();
    File file = new File(FILE_DIRECTORY_PATH);
    private static final Logger LOGGER = LoggerFactory.getLogger(NassaMenu.class);
    private Long id = 0L;
    private final ObjectMapper mapper = new ObjectMapper();
    private final static String MAIN_MENU = "---------------------------------------" +
            "\nMAIN MENU" +
            "\n1. Crew member operations" +
            "\n2. Spaceship operations" +
            "\n3. Planet operations" +
            "\n4. Flight mission operations" +
            "\n0. Exit" +
            "\n---------------------------------------";
    private final static String CREW_MEMBER_OPERATIONS = "---------------------------------------" +
            "\nCREW MEMBER OPERATIONS" +
            "\n1. Find all crew members" +
            "\n2. Find all crew members by rank" +
            "\n3. Find crew member by role" +
            "\n0. Back to main menu" +
            "\n---------------------------------------";
    private final static String SPACESHIP_OPERATIONS = "---------------------------------------" +
            "\nSPACESHIP OPERATIONS" +
            "\n1. Find all spaceships" +
            "\n2. Find all spaceships by flight distance" +
            "\n3. Find spaceship by flight distance" +
            "\n0. Back to main menu" +
            "\n---------------------------------------";
    private final static String PLANET_OPERATIONS = "---------------------------------------" +
            "\nPLANET OPERATIONS" +
            "\n1. Find all planets" +
            "\n2. Get random planet" +
            "\n3. Get distance between planets" +
            "\n0. Back to main menu" +
            "\n---------------------------------------";
    private final static String FLIGHT_MISSION_OPERATIONS = "---------------------------------------" +
            "\nFLIGHT MISSION OPERATIONS" +
            "\n1. Create flight mission" +
            "\n2. Read created mission" +
            "\n0. Back to main menu" +
            "\n---------------------------------------";

    @Override
    public String printAvailableOptions() {
        return MAIN_MENU;
    }

    @Override
    public void handleUserInput() {
        byte operation = -1;
        while (operation != 0) {
            System.out.println(printAvailableOptions());
            operation = scanner.nextByte();

            if (operation == 1) {
                crewMemberOperations(operation);
            }

            if (operation == 2) {
                spaceshipOperations(operation);
            }

            if (operation == 3) {
                planetOperations(operation);
            }

            if (operation == 4) {
                flightMissionOperations(operation);
            }
        }
    }

    @Override
    public void crewMemberOperations(byte operation) {
        while (operation != 0) {
            System.out.println(CREW_MEMBER_OPERATIONS);
            operation = scanner.nextByte();

            if (operation == 1) {
                crewService.findAllCrewMembers().forEach(System.out::println);
            }

            if (operation == 2) {
                crewService.findAllCrewMembersByCriteria(new CrewMemberCriteria
                        .CrewMemberCriteriaBuilder()
                        .byRank(Rank.resolveRankById((int) (Math.random() * (5 - 1) + 1)))
                        .build())
                        .forEach(System.out::println);
            }

            if (operation == 3) {
                System.out.println(crewService.findCrewMemberByCriteria(new CrewMemberCriteria
                        .CrewMemberCriteriaBuilder()
                        .byRole(Role.resolveRoleById((int) (Math.random() * (5 - 1) + 1)))
                        .build()
                ));
            }
        }
    }

    @Override
    public void spaceshipOperations(byte operation) {
        while (operation != 0) {
            System.out.println(SPACESHIP_OPERATIONS);
            operation = scanner.nextByte();

            if (operation == 1) {
                spaceshipService.findAllSpaceships().forEach(System.out::println);
            }

            if (operation == 2) {
                spaceshipService.findAllSpaceshipsByCriteria(new SpaceshipCriteria
                        .SpaceshipCriteriaBuilder()
                        .byFlightDistance((long) (Math.random() * (100015 - 100000) + 100000))
                        .build()).forEach(System.out::println);
            }

            if (operation == 3) {
                System.out.println(spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria
                        .SpaceshipCriteriaBuilder()
                        .byFlightDistance((long) (Math.random() * (100015 - 100000) + 100000))
                        .build()));
            }
        }
    }

    @Override
    public void planetOperations(byte operation) {
        while (operation != 0) {
            System.out.println(PLANET_OPERATIONS);
            operation = scanner.nextByte();

            if (operation == 1) {
                spacemapService.findAllPlanets().forEach(System.out::println);
            }

            if (operation == 2) {
                System.out.println(spacemapService.getRandomPlanet());
            }

            if (operation == 3) {
                Planet first = spacemapService.getRandomPlanet();
                Planet second = spacemapService.getRandomPlanet();

                System.out.println(first);
                System.out.println(second);

                System.out.println(spacemapService.getDistanceBetweenPlanets(first, second));
            }
        }
    }

    @Override
    public void flightMissionOperations(byte operation) {
        while (operation != 0) {
            System.out.println(FLIGHT_MISSION_OPERATIONS);
            operation = scanner.nextByte();

            if (operation == 1) {
                try {
                    writeJSONFile(missionService.createMission(new FlightMission(++id, "NiceMission", LocalDate.now(),
                            LocalDate.now(), 100001L)));
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                    id--;
                }
            }

            if (operation == 2) {
                try {
                    readJSONFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                missionService.findAllMissions().forEach(System.out::println);
            }
        }
    }

    @Override
    public void writeJSONFile(FlightMission flightMission) throws IOException {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(file, flightMission);
    }

    @Override
    public void readJSONFile() throws IOException {
        JsonNode root = new ObjectMapper().readTree(file);
        System.out.println(root);
    }

}