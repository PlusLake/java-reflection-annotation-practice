package webserver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.Test;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.annotation.Get;
import webserver.after.controller.Ship;

public class ReflectionTest {

    @Test
    public void scanMethodOfClass() {
        Stream.of(Ship.class.getDeclaredMethods())
                .map(Method::getName)
                .forEach(System.out::println);
        // result: getShips, addShip
    }

    @Test
    public void getAnnotationOfMethod() throws Exception {
        Method getShips = Ship.class.getMethod("getShips", HttpExchange.class);
        Annotation annotation = getShips.getAnnotations()[0];

        System.out.println(annotation);
        // result: @webserver.after.annotation.Get(value="/ship")

        System.out.println(getShips.getAnnotation(Get.class).value());
        // result: /ship
    }
}
