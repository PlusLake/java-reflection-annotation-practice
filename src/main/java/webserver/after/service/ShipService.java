package webserver.after.service;

import java.util.*;

import webserver.common.Ship;

public class ShipService {
    private List<Ship> ships = new ArrayList<>();

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

}
