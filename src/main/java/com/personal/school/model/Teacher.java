package com.personal.school.model;

import com.personal.school.form.TeacherForm;
import com.personal.school.service.ClassService;
import com.personal.school.service.SubjectService;
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

}
