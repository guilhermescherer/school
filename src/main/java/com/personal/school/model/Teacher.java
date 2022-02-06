package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Teacher extends People {

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private Schooling schooling;

    @ManyToMany(mappedBy = "teachers")
    private List<Class> classes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subjects_teacher",
            joinColumns = {@JoinColumn(name = "fk_teacher")},
            inverseJoinColumns = {@JoinColumn(name = "fk_subject")})
    private List<Subject> subjects;

    public Teacher() {
    }

    public Teacher(String name, String email, String telephone, String cpf, LocalDate birthDate, BigDecimal salary,
                   Schooling schooling, List<Class> classes, List<Subject> subjects) {
        super(name, email, telephone, cpf, birthDate);
        this.salary = salary;
        this.schooling = schooling;
        this.classes = classes;
        this.subjects = subjects;
    }
}
