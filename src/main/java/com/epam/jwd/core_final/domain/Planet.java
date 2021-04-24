package com.epam.jwd.core_final.domain;

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
    }

    private final Point x;
    private final Point y;

    public Planet(Long id, String name, Point x, Point y) {
        super(id, name);
        this.x = x;
        this.y = y;
    }

    public Point getX() {
        return x;
    }

    public Point getY() {
        return y;
    }

}