package webserver.before.controller;

import com.sun.net.httpserver.HttpExchange;

public class SystemController {
    public static String status(HttpExchange exchange) {
        return "System is running";
    }
}
