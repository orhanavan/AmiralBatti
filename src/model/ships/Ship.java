package model.ships;

public abstract class Ship {
    private int length;
    private String[] coordinates;
    private boolean alive = true;

    Ship(int length, String[] coordinates) {
        this.length = length;
        this.coordinates = coordinates;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
