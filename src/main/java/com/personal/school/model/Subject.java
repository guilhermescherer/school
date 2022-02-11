package com.personal.school.model;

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

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
    private List<Teacher> teachers;

    public Subject() { }

    public Subject(String name, List<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
    }
}
