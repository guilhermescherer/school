package com.personal.school.converter.impl;

import com.personal.school.converter.Converter;
import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;
import com.personal.school.model.TeachingType;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;

import static com.personal.school.utils.PropertyUtils.getNullProperties;
import static com.personal.school.utils.SetterUtils.setter;

public class ClassConverter implements Converter<ClassForm, Class> {

    private final TeacherService teacherService;
    private final StudentService studentService;

    public ClassConverter(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Override
    public Class convert(ClassForm source) {
        return convert(new Class(), source);
    }

    @Override
    public Class convert(Class target, ClassForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));

        final Function<String, TeachingType> teaching = TeachingType::valueOf;
        final Function<List<Long>, List<Teacher>> teachers = teacherService::getAllById;
        final Function<List<Long>, List<Student>> students = studentService::getAllById;

        setter(target::setTeachingType, source.getTeachingType(), teaching);
        setter(target::setTeachers, source.getTeachers(), teachers);
        setter(target::setStudents, source.getStudents(), students);

        target.getStudents().forEach(s -> s.setSchoolClass(target));

        return target;
    }
}
