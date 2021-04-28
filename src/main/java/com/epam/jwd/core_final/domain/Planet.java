package com.epam.jwd.core_final.domain;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity {

    public static class Point {
        private final int point;

        public Point(int point) {
            this.point = point;
        }

        public int getPoint() {
            return point;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "point=" + point +
                    '}';
        }
    }

    private final Point x;
    private final Point y;

    public Planet(Long id, String name, Point x, Point y) {
        super(id, name);
        this.x = x;
        this.y = y;
    }

    public Planet(Point x, Point y) {
        this.x = x;
        this.y = y;
    }

    public Point getX() {
        return x;
    }

    public Point getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(x, planet.x) && Objects.equals(y, planet.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Planet{" +
                super.toString() +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}