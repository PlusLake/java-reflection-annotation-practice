package webserver.after.controller;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.Logger;
import webserver.after.annotation.*;

@Component
public class SystemController {
    private final Logger logger;

    public SystemController(Logger logger) {
        this.logger = logger;
    }

    @Get("/status")
    public String status(HttpExchange exchange) {
        logger.log("SystemController");
        return "System is running";
    }
}
