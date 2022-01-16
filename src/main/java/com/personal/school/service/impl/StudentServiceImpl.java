package com.personal.school.service.impl;

import com.personal.school.form.StudentForm;
import com.personal.school.model.Class;
import com.personal.school.model.Student;
import com.personal.school.repository.StudentRepository;
import com.personal.school.service.ClassService;
import com.personal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;
import static java.lang.Math.toIntExact;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassService classService;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student toStudent(StudentForm studentForm) {
        Class schoolClass = classService.getByIdThrow(studentForm.getSchoolClass());
        LocalDate birthDate = LocalDate.parse(studentForm.getBirthDate(), getDefaultDateFormatter());

        return new Student(studentForm.getName(), studentForm.getEmail(), studentForm.getTelephone(),
                studentForm.getDocumentNumber(), birthDate, studentForm.getIsScholarshipHolder(), schoolClass);
    }

    @Override
    public Student update(Long id, StudentForm studentForm){
        Optional<Student> optionalStudent = getById(id);
        Student student;

        if(optionalStudent.isPresent()){
            student = optionalStudent.get();
            student.setName(studentForm.getName());
            student.setEmail(studentForm.getEmail());
            student.setTelephone(studentForm.getTelephone());
            student.setDocumentNumber(studentForm.getDocumentNumber());
            student.setBirthDate(LocalDate.parse(studentForm.getBirthDate(), getDefaultDateFormatter()));
            student.setIsScholarshipHolder(studentForm.getIsScholarshipHolder());
            student.setSchoolClass(classService.getByIdThrow(studentForm.getSchoolClass()));
        } else {
            throw new EmptyResultDataAccessException("Not found student", toIntExact(id));
        }

        return student;
    }

}
