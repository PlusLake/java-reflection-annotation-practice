package webserver.after.controller;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.annotation.Get;

public class InfoController {
    @Get("/info")
    public static String info(HttpExchange exchange) {
        return "System is running";
    }
}
