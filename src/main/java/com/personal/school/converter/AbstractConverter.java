package com.personal.school.converter;

public interface AbstractConverter<T, S> {

    S convert(S target, T source);
}
