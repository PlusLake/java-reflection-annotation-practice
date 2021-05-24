package webserver.after.controller;

import java.util.*;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.annotation.Get;

public class Info {
    private static final List<String> WIFES = new ArrayList<>();

    @Get("/info")
    public static String info(HttpExchange exchange) {
        return "System is running";
    }
}
