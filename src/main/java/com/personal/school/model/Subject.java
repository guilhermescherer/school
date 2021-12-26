package com.personal.school.model;

import com.personal.school.form.SubjectForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Course> courses;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
    private List<Teacher> teachers;

    public Subject() { }

    public Subject(String name) {
        this.name = name;
    }

    public static Subject toSubject(SubjectForm subjectForm) {
        return new Subject(subjectForm.getName());
    }
}
