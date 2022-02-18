package com.personal.school.converter;

import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.PeopleForm;
import com.personal.school.model.People;

public interface PeopleConverter {

    People toPeople(People target, PeopleForm source, ConvertMethod convertMethod);
}
