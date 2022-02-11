package com.personal.school.converter;

import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;

public interface ClassConverter {

    Class toClass(ClassForm classForm);

    Class toClass(Class schooClass, ClassForm classForm);
}
