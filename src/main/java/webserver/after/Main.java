package webserver.after;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import com.sun.net.httpserver.*;

import webserver.after.annotation.*;
import webserver.common.Exceptions.NotFoundException;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(21050), 0);
        server.createContext("/", Main::handle);
        Injector.inject();
        server.start();
    }

    public static void handle(HttpExchange exchange) throws IOException {
        try {
            Method method = Container.all().stream()
                    .map(Object::getClass)
                    .flatMap(c -> Stream.of(c.getDeclaredMethods()))
                    .filter(m -> methodMatches(exchange, m))
                    .filter(m -> pathMatches(exchange, m))
                    .findAny()
                    .orElseThrow(NotFoundException::new);
            Object instance = Container.all().stream()
                    .filter(object -> Arrays.asList(object.getClass().getDeclaredMethods()).contains(method))
                    .findAny().get();
            byte[] result = Optional.ofNullable(method.invoke(instance, exchange))
                    .map(Object::toString)
                    .map(String::getBytes)
                    .orElse(new byte[] {});
            exchange.sendResponseHeaders(200, result.length);
            exchange.getResponseBody().write(result);
        } catch (NotFoundException e) {
            exchange.sendResponseHeaders(404, 0);
        } catch (Exception e) {
            exchange.sendResponseHeaders(500, 0);
        }
        exchange.close();
    }

    private static boolean methodMatches(HttpExchange exchange, Method method) {
        if (exchange.getRequestMethod().equals("GET"))
            return method.getAnnotation(Get.class) != null;
        if (exchange.getRequestMethod().equals("POST"))
            return method.getAnnotation(Post.class) != null;
        return false;
    }

    private static boolean pathMatches(HttpExchange exchange, Method method) {
        Function<String, Boolean> isSamePath = path -> URI.create(path).getPath()
                .equals(exchange.getRequestURI().getPath());
        // Annotation は extends 不可のため以下のように分岐
        if (exchange.getRequestMethod().equals("GET"))
            return Optional.ofNullable(method.getAnnotation(Get.class))
                    .map(Get::value)
                    .map(isSamePath)
                    .orElse(false);
        if (exchange.getRequestMethod().equals("POST"))
            return Optional.ofNullable(method.getAnnotation(Post.class))
                    .map(Post::value)
                    .map(isSamePath)
                    .orElse(false);
        return false;
    }
}
