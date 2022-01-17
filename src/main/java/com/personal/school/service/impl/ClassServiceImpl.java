package com.personal.school.service.impl;

import com.personal.school.form.ClassForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;
import com.personal.school.model.TeachingType;
import com.personal.school.repository.ClassRepository;
import com.personal.school.service.ClassService;
import com.personal.school.service.StudentService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<Class> getAllByIdThrow(List<Long> ids){

        if(isNull(ids)) return EMPTY_LIST;

        List<Class> classes = classRepository.findAllById(ids);

        if(ids.size() != classes.size()) {
            throw new EmptyResultDataAccessException("Not found all classes", ids.size());
        }

        return classes;
    }

    public Class getByIdThrow(Long idClass) {
        Optional<Class> schoolClass = classRepository.findById(idClass);

        if(schoolClass.isPresent()){
            return schoolClass.get();
        } else {
            throw new EmptyResultDataAccessException("Not found class", toIntExact(idClass));
        }

    }

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Class> getById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Class toClass(ClassForm classForm, TeacherService teacherService, StudentService studentService) {
        TeachingType teachingType = TeachingType.valueOf(classForm.getTeachingType());
        List<Teacher> teachers = teacherService.getAllByIdThrow(classForm.getTeachers());
        List<Student> students = studentService.getAllByIdThrow(classForm.getStudents());

        return new Class(classForm.getName(), classForm.getQualificationNumber(), teachingType, teachers, students);
    }

    @Override
    public void save(Class schoolClass) {
        classRepository.save(schoolClass);
    }

    @Override
    public void remove(Long id) {
        classRepository.deleteById(id);
    }

    @Override
    public Class update(Long id, ClassForm classForm, TeacherService teacherService, StudentService studentService) {
        Optional<Class> optionalClass = getById(id);

        if(optionalClass.isPresent()){
            Class schoolClass = optionalClass.get();

            List<Teacher> teachers = teacherService.getAllByIdThrow(classForm.getTeachers());
            List<Student> students = studentService.getAllByIdThrow(classForm.getStudents());
            TeachingType teachingType = TeachingType.valueOf(classForm.getTeachingType());

            schoolClass.setName(classForm.getName());
            schoolClass.setQualificationNumber(classForm.getQualificationNumber());
            schoolClass.setTeachingType(teachingType);
            schoolClass.setTeachers(teachers);
            schoolClass.setStudents(students);

            return schoolClass;

        } else {
            throw new EmptyResultDataAccessException("Not found class", toIntExact(id));
        }

    }
}
