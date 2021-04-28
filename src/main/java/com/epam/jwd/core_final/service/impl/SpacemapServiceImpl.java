package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.EntityExistenceException;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.strategy.impl.SpacemapReadingStrategy;

import java.util.Collection;
import java.util.List;

public class SpacemapServiceImpl implements SpacemapService {

    private final Collection<Planet> planets = NassaContext.getInstance().retrieveBaseEntityList(Planet.class);

    private SpacemapServiceImpl() {
    }

    private static class SpacemapServiceImplHolder {
        private static final SpacemapServiceImpl instance = new SpacemapServiceImpl();
    }

    public static SpacemapServiceImpl getInstance() {
        return SpacemapServiceImplHolder.instance;
    }

    @Override
    public Collection<Planet> findAllPlanets() {
        return planets;
    }

    @Override
    public Planet getRandomPlanet() {
        List<Planet> planetList = (List<Planet>) planets;
        return planetList.get((int) (Math.random() * 37));
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return (int) Math.sqrt(Math.pow(second.getX().getPoint() - first.getX().getPoint(), 2) +
                Math.pow(second.getY().getPoint() - first.getX().getPoint(), 2));
    }

    @Override
    public List<Planet> createPlanet(SpacemapReadingStrategy spacemapReadingStrategy) throws EntityExistenceException {
        return spacemapReadingStrategy.getEntities();
    }

}