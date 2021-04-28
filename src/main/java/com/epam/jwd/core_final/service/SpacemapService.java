package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.strategy.impl.SpacemapReadingStrategy;

import java.util.Collection;
import java.util.List;

public interface SpacemapService {

    Collection<Planet> findAllPlanets();

    Planet getRandomPlanet();

    // Dijkstra ?
    int getDistanceBetweenPlanets(Planet first, Planet second);

    List<Planet> createPlanet(SpacemapReadingStrategy spacemapReadingStrategy) throws EntityExistenceException;

}
