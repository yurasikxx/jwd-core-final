package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
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

public class CrewReadingStrategy implements FileReadingStrategy<CrewMember> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewReadingStrategy.class);
    private final List<CrewMember> crewMembers = new ArrayList<>();
    private Long counter = 0L;

    private CrewReadingStrategy() {
    }

    private static class CrewReadingStrategyHolder {
        private final static CrewReadingStrategy instance = new CrewReadingStrategy();
    }

    public static CrewReadingStrategy getInstance() {
        return CrewReadingStrategyHolder.instance;
    }

    @Override
    public void readFromFile(String path) throws FileNotFoundException {
        Map<String, CrewMember> tempMap = new HashMap<>();
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        Scanner scanner = new Scanner(new File(path));

        scanner.nextLine();
        scanner.useDelimiter(";");

        while (scanner.hasNext()) {
            String dividedByComma = scanner.next();
            String[] splitByComma = dividedByComma.split(",");
            Role role = Role.resolveRoleById(Integer.parseInt(splitByComma[0]));
            String name = splitByComma[1];
            Rank rank = Rank.resolveRankById(Integer.parseInt(splitByComma[2]));
            Long id = ++counter;

            if (!tempMap.containsKey(name)) {
                crewMembers.add(crewMemberFactory.create(id, name, role, rank));
                tempMap.put(name, null);
            } else {
                try {
                    throw new EntityExistenceException("Crew member " + name + " already exists");
                } catch (EntityExistenceException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<CrewMember> getEntities() {
        return crewMembers;
    }

}