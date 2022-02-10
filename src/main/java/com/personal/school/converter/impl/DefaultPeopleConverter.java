package com.personal.school.converter.impl;

import com.personal.school.converter.PeopleConverter;
import com.personal.school.form.PeopleUpdateForm;
import com.personal.school.model.People;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

@Component
public class DefaultPeopleConverter implements PeopleConverter {

    @Override
    public People toPeople(People people, PeopleUpdateForm peopleForm) {
        populateName(people, peopleForm);
        populateEmail(people, peopleForm);
        populateTelephone(people, peopleForm);
        populateCpf(people, peopleForm);
        populateBirthDate(people, peopleForm);

        return people;
    }

    private void populateBirthDate(People people, PeopleUpdateForm peopleForm) {
        String birthDate = peopleForm.getBirthDate();
        if(birthDate != null) {
            people.setBirthDate(LocalDate.parse(birthDate, getDefaultDateFormatter()));
        }
    }

    private void populateCpf(People people, PeopleUpdateForm peopleForm) {
        String cpf = peopleForm.getCpf();
        if(cpf != null) {
            people.setCpf(getCpfUnformat(peopleForm.getCpf()));
        }
    }

    private void populateTelephone(People people, PeopleUpdateForm peopleForm) {
        String telephone = peopleForm.getTelephone();
        if(telephone != null) {
            people.setTelephone(telephone);
        }
    }

    private void populateEmail(People people, PeopleUpdateForm peopleForm) {
        String email = peopleForm.getEmail();
        if(email != null) {
            people.setEmail(email);
        }
    }

    private void populateName(People people, PeopleUpdateForm peopleForm) {
        String name = peopleForm.getName();
        if(name != null) {
            people.setName(name);
        }
    }
}
