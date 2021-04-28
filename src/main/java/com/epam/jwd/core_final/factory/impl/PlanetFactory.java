package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

public class PlanetFactory implements EntityFactory<Planet> {
    @Override
    public Planet create(Object... args) {
        return new Planet((Long) args[0], (String) args[1], (Planet.Point) args[2], (Planet.Point) args[3]);
    }
}
