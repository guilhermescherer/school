package com.personal.school.model;

import com.personal.school.form.TeacherForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

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

    public Teacher(TeacherForm teacherForm) {
        super(teacherForm);
        this.setSchooling(Schooling.valueOf(teacherForm.getSchooling()));
    }

    public static Teacher toTeacher(TeacherForm teacherForm) {
        return new Teacher(teacherForm);
    }
}
