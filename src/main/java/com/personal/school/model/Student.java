package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Student extends People {

    private Boolean isScholarshipHolder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = true)
    private Class schoolClass;

    public Student() { }

    public Student(String name, String email, String telephone, String documentNumber, LocalDate birthDate, Boolean isScholarshipHolder) {
        super(name, email, telephone, documentNumber, birthDate);
        this.isScholarshipHolder = isScholarshipHolder;
    }

    public Student(String name, String email, String telephone, String documentNumber, LocalDate birthDate, Boolean isScholarshipHolder, Class schoolClass) {
        super(name, email, telephone, documentNumber, birthDate);
        this.isScholarshipHolder = isScholarshipHolder;
        this.schoolClass = schoolClass;
    }

}
