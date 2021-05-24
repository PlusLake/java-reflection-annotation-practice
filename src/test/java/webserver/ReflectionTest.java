package webserver;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.stream.Stream;

import org.junit.*;
import org.junit.rules.TestName;

import com.sun.net.httpserver.HttpExchange;

import webserver.after.annotation.Get;
import webserver.after.controller.ShipController;
import webserver.common.Ship;

public class ReflectionTest {
    @Rule
    public TestName name = new TestName();

    @Before
    public void before() {
        System.out.printf("【%s】\n", name.getMethodName());
    }

    @After
    public void after() {
        System.out.println();
    }

    @Test
    public void scanMethodOfClass() {
        Stream.of(ShipController.class.getDeclaredMethods())
                .map(Method::getName)
                .forEach(System.out::println);
        // result: getShips, addShip
    }

    @Test
    public void getAnnotationOfMethod() throws Exception {
        Method getShips = ShipController.class.getMethod("getShips", HttpExchange.class);
        Annotation annotation = getShips.getAnnotations()[0];

        System.out.println(annotation);
        // result: @webserver.after.annotation.Get(value="/ship")

        System.out.println(getShips.getAnnotation(Get.class).value());
        // result: /ship
    }

    @Test
    public void getClassFields() {
        Stream.of(Ship.class.getDeclaredFields())
                .map(Field::getName)
                .forEach(System.out::println);
    }
}
