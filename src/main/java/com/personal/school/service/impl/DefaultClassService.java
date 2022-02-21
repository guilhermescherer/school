package com.personal.school.service.impl;

import com.personal.school.exception.NotFoundException;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;
import com.personal.school.repository.ClassRepository;
import com.personal.school.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class DefaultClassService implements ClassService {

    private final ClassRepository classRepository;

    @Autowired
    public DefaultClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public List<Class> getAll() {
        final List<Class> classes = classRepository.findAll();
        if(isEmpty(classes)) {
            throw new NotFoundException("Not found any class");
        }
        return classes;
    }

    @Override
    public List<Class> getAllById(@NotNull List<Long> ids){
        List<Class> classes = classRepository.findAllById(ids);

        if(ids.size() != classes.size()) {
            throw new NotFoundException("Not found all classes");
        }

        return classes;
    }

    @Override
    public Class getById(Long id) {
        Optional<Class> schoolClass = classRepository.findById(id);

        if(schoolClass.isPresent()){
            return schoolClass.get();
        } else {
            throw new NotFoundException("Not found class");
        }
    }

    @Override
    public Class save(Class schoolClass) {
        classRepository.save(schoolClass);
        return schoolClass;
    }

    @Override
    public void remove(Class schoolClass) {
        classRepository.delete(schoolClass);
    }


    @Override
    public void saveStudents(Class schoolClass, List<Student> students) {
        List<Student> currentStudents = schoolClass.getStudents();
        currentStudents.addAll(students);
    }

    @Override
    public void saveTeachers(Class schoolClass, List<Teacher> teachers) {
        List<Teacher> currentTeachers = schoolClass.getTeachers();
        currentTeachers.addAll(teachers);
    }

    @Override
    public void removeStudents(Class schoolClass, List<Student> students) {
        List<Student> currentStudents = schoolClass.getStudents();
        currentStudents.removeAll(students);
    }

    @Override
    public void removeTeachers(Class schoolClass, List<Teacher> teachers) {
        List<Teacher> currentTeachers = schoolClass.getTeachers();
        currentTeachers.removeAll(teachers);
    }
}
