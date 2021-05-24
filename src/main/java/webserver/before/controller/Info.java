package webserver.before.controller;

import com.sun.net.httpserver.HttpExchange;

public class Info {
    public static String info(HttpExchange exchange) {
        return "System is running";
    }
}
