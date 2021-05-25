package webserver.after;

import webserver.after.annotation.Component;

/** 依存関係を面倒くさくするためのもの */
@Component
public class Logger {
    public void log(String message) {
        System.out.println(message);
    }
}
