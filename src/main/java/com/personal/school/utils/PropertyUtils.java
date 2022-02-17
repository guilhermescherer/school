package com.personal.school.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class PropertyUtils {

    public static String[] getNullProperties(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> isNull(wrappedSource.getPropertyValue(propertyName)))
                .toArray(String[]::new);
    }
}
