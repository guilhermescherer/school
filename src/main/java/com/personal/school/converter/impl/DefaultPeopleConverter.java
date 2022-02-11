package com.personal.school.converter.impl;

import com.personal.school.converter.ConvertMethod;
import com.personal.school.converter.PeopleConverter;
import com.personal.school.form.PeopleForm;
import com.personal.school.model.People;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.personal.school.utils.ConverterUtils.isValidSet;
import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

@Component
public class DefaultPeopleConverter implements PeopleConverter {

    @Override
    public People toPeople(People target, PeopleForm source, ConvertMethod convertMethod) {
        populateName(target, source.getName(), convertMethod);
        populateEmail(target, source.getEmail(), convertMethod);
        populateTelephone(target, source.getTelephone(), convertMethod);
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

    private void populateTelephone(People people, String telephone, ConvertMethod convertMethod) {
        if(isValidSet(telephone, convertMethod)) {
            people.setTelephone(telephone);
        }
    }

    private void populateEmail(People people, String email, ConvertMethod convertMethod) {
        if(isValidSet(email, convertMethod)) {
            people.setEmail(email);
        }
    }

    private void populateName(People people, String name, ConvertMethod convertMethod) {
        if(isValidSet(name, convertMethod)) {
            people.setName(name);
        }
    }
}
