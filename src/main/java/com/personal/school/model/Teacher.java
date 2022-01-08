package com.personal.school.model;

import com.personal.school.form.TeacherForm;
import com.personal.school.repository.SubjectRepository;
import com.personal.school.repository.TeacherRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;
import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
public class Teacher extends People {

    @Enumerated(EnumType.STRING)
    private Schooling schooling;

    @ManyToMany(mappedBy = "teachers")
    private List<Class> classes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subjects_teacher",
            joinColumns = { @JoinColumn(name = "fk_teacher") },
            inverseJoinColumns = { @JoinColumn(name = "fk_subject") })
    private List<Subject> subjects;

    public Teacher() { }

    public Teacher(String name, String email, String telephone, String documentNumber, LocalDate birthDate,
                   Schooling schooling, List<Class> classes, List<Subject> subjects) {
        super(name, email, telephone, documentNumber, birthDate);
        this.schooling = schooling;
        this.classes = classes;
        this.subjects = subjects;
    }

    public static Teacher toTeacher(TeacherForm teacherForm, SubjectRepository subjectRepository) {
        List<Subject> subjects = getSubjects(teacherForm, subjectRepository);
        List<Class> classes = new ArrayList<>();
        Schooling schooling = Schooling.valueOf(teacherForm.getSchooling());
        LocalDate birthDate = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());

        return new Teacher(teacherForm.getName(), teacherForm.getEmail(), teacherForm.getTelephone(),
                teacherForm.getDocumentNumber(), birthDate, schooling, classes, subjects);
    }

    private static List<Subject> getSubjects(TeacherForm teacherForm, SubjectRepository subjectRepository) {
        List<Subject> subjects = new ArrayList<>();
        if(nonNull(teacherForm.getSubjects())){
            subjects = subjectRepository.findAllById(teacherForm.getSubjects());
        }
        return subjects;
    }

}
