package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

}