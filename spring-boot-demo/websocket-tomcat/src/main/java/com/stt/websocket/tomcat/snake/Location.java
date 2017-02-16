package com.stt.websocket.tomcat.snake;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class Location {

    public int x;
    public int y;
    public static final int GRID_SIZE = 10;
    public static final int PLAYFIELD_HEIGHT = 480;
    public static final int PLAYFIELD_WIDTH = 640;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location getAdjacentLocation(Direction direction){
        switch (direction){
            case NORTH:
                return new Location(this.x, this.y - Location.GRID_SIZE);
            case SOUTH:
                return new Location(this.x, this.y + Location.GRID_SIZE);
            case EAST:
                return new Location(this.x + Location.GRID_SIZE, this.y);
            case WEST:
                return new Location(this.x - Location.GRID_SIZE, this.y);
            case NONE:
                // fall through
            default:
                return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        return y == location.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
