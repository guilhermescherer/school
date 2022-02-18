package com.personal.school.converter.impl;

import com.personal.school.enums.ConvertMethod;
import com.personal.school.converter.PeopleConverter;
import com.personal.school.form.PeopleForm;
import com.personal.school.model.People;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.personal.school.utils.ConverterUtils.isValidSet;
import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;
import static com.personal.school.utils.PropertyUtils.getNullProperties;

@Component
public class DefaultPeopleConverter implements PeopleConverter {

    @Override
    public People toPeople(People target, PeopleForm source, ConvertMethod convertMethod) {
        if(convertMethod.equals(ConvertMethod.ADD)) {
            BeanUtils.copyProperties(source, target);
        } else {
            BeanUtils.copyProperties(source, target, getNullProperties(source));
        }

        populateCpf(target, source.getCpf(), convertMethod);
        populateBirthDate(target, source.getBirthDate(), convertMethod);

        return target;
    }

    private void populateBirthDate(People people, String birthDate, ConvertMethod convertMethod) {
        if(isValidSet(birthDate, convertMethod)) {
            people.setBirthDate(LocalDate.parse(birthDate, getDefaultDateFormatter()));
        }
    }

    private void populateCpf(People people, String cpf, ConvertMethod convertMethod) {
        if(isValidSet(cpf, convertMethod)) {
            people.setCpf(getCpfUnformat(cpf));
        }
    }
}
