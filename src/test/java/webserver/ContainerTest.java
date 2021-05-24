package webserver;

import java.util.List;
import java.util.stream.*;

import org.junit.Test;

import webserver.after.*;

public class ContainerTest {
    @Test
    public void containerTest() {
        Container.set("Hello World");
        String string = Container.get(String.class);
        System.out.println(string);
    }

    @Test
    public void injectionTest() {
        Injector.inject();
        System.out.println(Container.size());
    }

    @Test
    public void streamNullTest() {
        List<String> list = IntStream.range(1, 11)
                .mapToObj(i -> i % 3 == 0 ? null : "!")
                .collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list.contains(null));
    }
}
