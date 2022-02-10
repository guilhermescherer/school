package com.personal.school.converter;

import com.personal.school.form.PeopleUpdateForm;
import com.personal.school.model.People;

public interface PeopleConverter {

    People toPeople(People people, PeopleUpdateForm peopleForm);
}
