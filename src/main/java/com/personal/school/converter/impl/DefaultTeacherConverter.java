package com.personal.school.converter.impl;

import com.personal.school.converter.PeopleConverter;
import com.personal.school.converter.TeacherConverter;
import com.personal.school.form.TeacherForm;
import com.personal.school.form.TeacherUpdateForm;
import com.personal.school.model.Schooling;
import com.personal.school.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.personal.school.utils.FormatterUtils.getCpfUnformat;
import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

@Component
public class DefaultTeacherConverter implements TeacherConverter {

    @Autowired
    PeopleConverter peopleConverter;

    @Override
    public Teacher toTeacher(TeacherForm teacherForm) {
        LocalDate birthDate = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());
        Schooling schooling = Schooling.valueOf(teacherForm.getSchooling());
        String cpf = getCpfUnformat(teacherForm.getCpf());
        BigDecimal salary = BigDecimal.valueOf(teacherForm.getSalary());

        return new Teacher(teacherForm.getName(), teacherForm.getEmail(), teacherForm.getTelephone(), cpf, birthDate,
                salary, schooling);
    }

    @Override
    public Teacher toTeacher(Teacher teacher, TeacherUpdateForm teacherUpdateForm) {
        Teacher teacherPopulated = (Teacher) peopleConverter.toPeople(teacher, teacherUpdateForm);

        populateSchooling(teacherPopulated, teacherUpdateForm);

        return teacherPopulated;
    }

    private void populateSchooling(Teacher teacher, TeacherUpdateForm teacherUpdateForm) {
        String schooling = teacherUpdateForm.getSchooling();
        if(schooling != null){
            teacher.setSchooling(Schooling.valueOf(schooling));
        }
    }
}
