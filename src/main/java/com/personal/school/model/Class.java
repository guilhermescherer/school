package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer qualificationNumber;
    @Enumerated(EnumType.STRING)
    private TeachingType teachingType;

    @ManyToMany()
    @JoinTable(name = "teachers_class",
            joinColumns = { @JoinColumn(name = "fk_teacher") },
            inverseJoinColumns = { @JoinColumn(name = "fk_class") }
    )
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "schoolClass")
    private List<Student> students;

    public Class() { }

    public Class(String name, Integer qualificationNumber, TeachingType teachingType, List<Teacher> teachers, List<Student> students) {
        this.name = name;
        this.qualificationNumber = qualificationNumber;
        this.teachingType = teachingType;
        this.teachers = teachers;
        this.students = students;
    }
}
