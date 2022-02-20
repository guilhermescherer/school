package com.personal.school.converter;

import com.personal.school.enums.ConvertMethod;

public interface AbstractConverter<T, S> {

    S convert(S target, T source, ConvertMethod convertMethod);

}
