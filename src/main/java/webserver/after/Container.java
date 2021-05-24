package webserver.after;

import java.util.*;

public class Container {
    private static final Map<Class<?>, Object> container = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> type) {
        return (T) container.get(type);
    }

    public static void set(Object object) {
        container.put(object.getClass(), object);
    }

    public static int size() {
        return container.size();
    }

    public static Collection<Object> all() {
        return container.values();
    }

}
