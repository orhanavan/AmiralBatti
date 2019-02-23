package model.user;

import model.ships.Ship;

import java.util.List;

public class Player {
    private String name;
    private List<Ship> ships;

    public Player(String name, List<Ship> ships) {
        this.name = name;
        this.ships = ships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
