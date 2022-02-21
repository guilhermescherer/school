package com.personal.school.service;

import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.model.Teacher;

import java.util.List;

public interface ClassService {

    List<Class> getAll();

    List<Class> getAllById(List<Long> idList);

    Class getById(Long id);

    Class save(Class schoolClass);

    void remove(Class schoolClass);

    void saveStudents(Class schoolClass, List<Student> students);

    void saveTeachers(Class schoolClass, List<Teacher> teachers);

    void removeStudents(Class schoolClass, List<Student> students);

    void removeTeachers(Class schoolClass, List<Teacher> teachers);
}
