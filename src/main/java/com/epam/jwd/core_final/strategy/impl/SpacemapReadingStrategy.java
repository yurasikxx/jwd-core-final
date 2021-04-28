package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.strategy.FileReadingStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpacemapReadingStrategy implements FileReadingStrategy<Planet> {

    private final List<Planet> planets = new ArrayList<>();

    private SpacemapReadingStrategy() {
    }

    private static class SpacemapReadingStrategyHolder {
        private final static SpacemapReadingStrategy instance = new SpacemapReadingStrategy();
    }

    public static SpacemapReadingStrategy getInstance() {
        return SpacemapReadingStrategyHolder.instance;
    }

    @Override
    public void readFromFile(String path) throws FileNotFoundException {
        PlanetFactory planetFactory = new PlanetFactory();
        Scanner scanner = new Scanner(new File(path));

        while (scanner.hasNext()) {
            String dividedByComma = scanner.next();
            String[] splitByComma = dividedByComma.split(",");
            Long id = 0L;

            for (String s : splitByComma) {
                if (!s.equals("null")) {
                    planets.add(planetFactory.create(id, s, new Planet.Point((int) (Math.random() * 250)), new Planet.Point((int) (Math.random() * 150))));
                }
                id++;
            }
        }
    }

    @Override
    public List<Planet> getEntities() {
        return planets;
    }

}
