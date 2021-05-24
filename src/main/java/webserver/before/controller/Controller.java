package webserver.before.controller;

import java.net.URI;
import java.util.*;
import java.util.function.*;

import com.sun.net.httpserver.HttpExchange;

import lombok.*;

@AllArgsConstructor
@Getter
public class Controller {
    private String method;
    private URI path;
    private Function<HttpExchange, Object> function;

    public boolean matches(URI uri, String method) {
        return path.getPath().equals(uri.getPath()) && this.method.equals(method);
    }

    public static class ControllerInitializer {
        private List<Controller> controllers = new ArrayList<>();

        public ControllerInitializer create(String httpMethod, String path, Function<HttpExchange, Object> function) {
            this.controllers.add(new Controller(httpMethod, URI.create(path), function));
            return this;
        }

        public ControllerInitializer create(String httpMethod, String path, Consumer<HttpExchange> consumer) {
            this.controllers.add(new Controller(httpMethod, URI.create(path), exchange -> {
                consumer.accept(exchange);
                return null;
            }));
            return this;
        }

        public List<Controller> done() {
            return controllers;
        }
    }
}