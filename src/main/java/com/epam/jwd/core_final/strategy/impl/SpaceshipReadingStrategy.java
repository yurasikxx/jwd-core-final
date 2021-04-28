package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.strategy.FileReadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SpaceshipReadingStrategy implements FileReadingStrategy<Spaceship> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceshipReadingStrategy.class);
    private final List<Spaceship> spaceships = new ArrayList<>();
    private Long id = 0L;

    private SpaceshipReadingStrategy() {
    }

    private static class SpaceshipReadingStrategyHolder {
        private final static SpaceshipReadingStrategy instance = new SpaceshipReadingStrategy();
    }

    public static SpaceshipReadingStrategy getInstance() {
        return SpaceshipReadingStrategyHolder.instance;
    }

    @Override
    public void readFromFile(String path) throws FileNotFoundException {
        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        Map<String, Spaceship> tempMap = new HashMap<>();
        Scanner scanner = new Scanner(new File(path));

        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String delimitedBySemicolon = scanner.nextLine();
            String[] splitBySemicolon = delimitedBySemicolon.split(";");
            String name = splitBySemicolon[0];
            Long distance = Long.parseLong(splitBySemicolon[1]);
            String[] mapFields = splitBySemicolon[2].substring(1, splitBySemicolon[2].length() - 1).split(",");
            Map<Role, Short> crewMembers = new HashMap<>();

            for (String pairCounter : mapFields) {
                String[] roleCounter = pairCounter.split(":");
                crewMembers.put(Role.resolveRoleById(Integer.parseInt(roleCounter[0])), Short.parseShort(roleCounter[1]));
            }

            if (!tempMap.containsKey(name)) {
                spaceships.add(spaceshipFactory.create(++id, name, crewMembers, distance));
                tempMap.put(name, null);
            } else {
                try {
                    throw new EntityExistenceException("Spaceship " + name + " already exists");
                } catch (EntityExistenceException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<Spaceship> getEntities() {
        return spaceships;
    }

}