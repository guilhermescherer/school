package com.personal.school.utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class SetterUtils {

    public static <T, R> void setter(Consumer<R> setter, T value, Function<T, R> function) {
        if(value != null) {
            final R functionValue = function.apply(value);
            setter.accept(functionValue);
        }
    }

    public static <T> void setter(Consumer<T> setter, T value) {
        if(value != null) {
            setter.accept(value);
        }
    }
}
