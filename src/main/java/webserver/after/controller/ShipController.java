package webserver.after.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.Logger;
import webserver.after.annotation.*;
import webserver.after.service.ShipService;
import webserver.common.*;
import webserver.common.Ship.ShipType;

public class ShipController {
    private final ShipService service;
    private final Logger logger;

    public ShipController(Logger logger, ShipService service) {
        this.service = service;
        this.logger = logger;
    }

    @Get("/ship")
    public List<Ship> getShips(HttpExchange exchange) {
        logger.log(LocalDateTime.now() + " ShipController: getShips");
        return this.service.getShips();
    }

    @Post("/ship")
    public void addShip(HttpExchange exchange) {
        logger.log(LocalDateTime.now() + " ShipController: addShip");
        String body = Utility.read(exchange);
        this.service.addShip(new Ship(body, ShipType.HEAVY_CRUISER));
    }
}
