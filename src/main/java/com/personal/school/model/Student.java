package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Student extends People{

    private Boolean isScholarshipHolder;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "courses_student",
            joinColumns = { @JoinColumn(name = "fk_course") },
            inverseJoinColumns = { @JoinColumn(name = "fk_student")}
    )
    private List<Course> courses;

}
