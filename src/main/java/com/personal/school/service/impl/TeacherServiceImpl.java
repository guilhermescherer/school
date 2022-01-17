package com.personal.school.service.impl;

import com.personal.school.form.TeacherForm;
import com.personal.school.model.Class;
import com.personal.school.model.Schooling;
import com.personal.school.model.Subject;
import com.personal.school.model.Teacher;
import com.personal.school.repository.TeacherRepository;
import com.personal.school.service.ClassService;
import com.personal.school.service.SubjectService;
import com.personal.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;
import static java.lang.Math.toIntExact;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getAllByIdThrow(List<Long> ids) {

        if(isNull(ids)) return EMPTY_LIST;

        List<Teacher> teachers = teacherRepository.findAllById(ids);

        if(ids.size() != teachers.size()) {
            throw new EmptyResultDataAccessException("Not found all teachers", ids.size());
        }

        return teachers;
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void remove(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher toTeacher(TeacherForm teacherForm, SubjectService subjectService, ClassService classService) {

        List<Subject> subjects = subjectService.getAllByIdThrow(teacherForm.getSubjects());
        List<Class> classes = classService.getAllByIdThrow(teacherForm.getClasses());

        Schooling schooling = Schooling.valueOf(teacherForm.getSchooling());
        LocalDate birthDate = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());

        return new Teacher(teacherForm.getName(), teacherForm.getEmail(), teacherForm.getTelephone(),
                teacherForm.getDocumentNumber(), birthDate, schooling, classes, subjects);
    }

    @Override
    public Teacher update(Long id, TeacherForm teacherForm, SubjectService subjectService, ClassService classService) {

        Optional<Teacher> optionalTeacher = getById(id);

        if(optionalTeacher.isPresent()){

            Teacher teacher = optionalTeacher.get();

            List<Subject> subjects = subjectService.getAllByIdThrow(teacherForm.getSubjects());
            List<Class> classes = classService.getAllByIdThrow(teacherForm.getClasses());
            LocalDate birthDate = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());
            Schooling schooling = Schooling.valueOf(teacherForm.getSchooling());

            teacher.setName(teacherForm.getName());
            teacher.setEmail(teacherForm.getEmail());
            teacher.setTelephone(teacherForm.getTelephone());
            teacher.setDocumentNumber(teacherForm.getDocumentNumber());
            teacher.setBirthDate(birthDate);
            teacher.setSchooling(schooling);
            teacher.setSubjects(subjects);
            teacher.setClasses(classes);

            return teacher;
        } else {
            throw new EmptyResultDataAccessException("Not found subject", toIntExact(id));
        }
    }

}
