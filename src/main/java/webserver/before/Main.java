package webserver.before;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.sun.net.httpserver.*;

import webserver.before.controller.*;
import webserver.before.controller.Controller.ControllerInitializer;
import webserver.common.Exceptions.NotFoundException;

public class Main {
    private static List<Controller> controllers;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(21050), 0);
        server.createContext("/", Main::handle);
        controllers = new ControllerInitializer()
                .create("GET", "/ship", Ship::getShips)
                .create("POST", "/ship", Ship::addShip)
                .create("GET", "/info", Info::info)
                .done();
        server.start();
    }

    public static void handle(HttpExchange exchange) throws IOException {
        try {
            Controller controller = controllers.stream()
                    .filter(c -> c.matches(exchange.getRequestURI(), exchange.getRequestMethod()))
                    .findAny()
                    .orElseThrow(NotFoundException::new);
            byte[] result = Optional.ofNullable(controller.getFunction().apply(exchange))
                    .map(Object::toString)
                    .orElse("")
                    .getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, result.length);
            exchange.getResponseBody().write(result);
        } catch (NotFoundException e) {
            exchange.sendResponseHeaders(404, 0);
        } catch (Exception e) {
            exchange.sendResponseHeaders(500, 0);
        }
        exchange.close();
    }
}
