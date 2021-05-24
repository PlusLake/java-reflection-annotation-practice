package webserver.after.controller;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.Logger;
import webserver.after.annotation.Get;

public class SystemController {
    private final Logger logger;

    public SystemController(Logger logger) {
        this.logger = logger;
    }

    @Get("/info")
    public String info(HttpExchange exchange) {
        logger.log("SystemController");
        return "System is running";
    }
}
