package com.personal.school.converter;

public interface Converter<T, S> {

    S convert(T source);

    S convert(S target, T source);
}
