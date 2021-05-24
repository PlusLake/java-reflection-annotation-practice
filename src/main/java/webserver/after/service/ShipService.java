package webserver.after.service;

import java.time.LocalDateTime;
import java.util.*;

import webserver.after.Logger;
import webserver.common.Ship;

public class ShipService {
    private List<Ship> ships = new ArrayList<>();
    private final Logger logger;

    public ShipService(Logger logger) {
        this.logger = logger;
    }

    public List<Ship> getShips() {
        logger.log(LocalDateTime.now() + " ShipService: getShips");
        return ships;
    }

    public void addShip(Ship ship) {
        logger.log(LocalDateTime.now() + " ShipService: addShip");
        ships.add(ship);
    }

}
