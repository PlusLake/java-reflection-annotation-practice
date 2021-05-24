package webserver.before.controller;

import java.io.IOException;
import java.util.*;

import com.sun.net.httpserver.HttpExchange;

public class Ship {
    private static final List<String> SHIPS = new ArrayList<>();

    public static String getShips(HttpExchange exchange) {
        return SHIPS.toString();
    }

    public static void addShip(HttpExchange exchange) {
        try {
            String newShip = new String(exchange.getRequestBody().readAllBytes());
            SHIPS.add(newShip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
