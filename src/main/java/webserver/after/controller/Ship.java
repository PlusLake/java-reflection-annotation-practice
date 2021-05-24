package webserver.after.controller;

import java.io.IOException;
import java.util.*;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.annotation.*;

public class Ship {
    private static final List<String> WIFES = new ArrayList<>();

    @Get("/ship")
    public static String getShips(HttpExchange exchange) {
        return WIFES.toString();
    }

    @Post("/ship")
    public static void addShip(HttpExchange exchange) {
        try {
            String newWife = new String(exchange.getRequestBody().readAllBytes());
            WIFES.add(newWife);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
