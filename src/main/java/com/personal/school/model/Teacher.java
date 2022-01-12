package com.personal.school.model;

import com.personal.school.form.TeacherForm;
import com.personal.school.service.ClassService;
import com.personal.school.service.SubjectService;
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

    public Teacher(String name, String email, String telephone, String documentNumber, LocalDate birthDate,
                   Schooling schooling, List<Class> classes, List<Subject> subjects) {
        super(name, email, telephone, documentNumber, birthDate);
        this.schooling = schooling;
        this.classes = classes;
        this.subjects = subjects;
    }

    public static Teacher toTeacher(TeacherForm teacherForm, SubjectService subjectService, ClassService classService) {
        List<Subject> subjects = subjectService.getAllById(teacherForm.getSubjects());
        List<Class> classes = classService.getClassesByListId(teacherForm.getClasses());

        Schooling schooling = Schooling.valueOf(teacherForm.getSchooling());
        LocalDate birthDate = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());

        return new Teacher(teacherForm.getName(), teacherForm.getEmail(), teacherForm.getTelephone(),
                teacherForm.getDocumentNumber(), birthDate, schooling, classes, subjects);
    }

}
