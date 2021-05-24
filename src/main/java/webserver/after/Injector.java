package webserver.after;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import webserver.after.controller.*;
import webserver.after.service.ShipService;

public class Injector {
    private static final Comparator<Class<?>> LESS_PARAMETER = (a, b) -> a.getConstructors()[0].getParameterCount()
            - b.getConstructors()[0].getParameterCount();
    private static final List<Class<?>> COMPONENTS = List.of(
            ShipController.class, ShipService.class, SystemController.class, Logger.class);

    public static void inject() {
        while (Container.size() != COMPONENTS.size()) {
            int size = Container.size();
            COMPONENTS.stream()
                    .filter(type -> Container.get(type) == null)
                    .sorted(LESS_PARAMETER)
                    .forEach(Injector::newInstance);
            if (Container.size() == size) {
                // 循環依存などによる初期化失敗
                return;
            }
        }
    }

    private static void newInstance(Class<?> type) {
        Constructor<?> constructor = type.getConstructors()[0];
        List<Object> parameters = Stream.of(constructor.getParameters())
                .map(Parameter::getType)
                .map(Container::get)
                .collect(Collectors.toList());
        if (parameters.contains(null))
            return;
        try {
            Container.set(constructor.newInstance(parameters.toArray(Object[]::new)));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
