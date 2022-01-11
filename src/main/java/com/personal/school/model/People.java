package com.personal.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String documentNumber;
    private LocalDate birthDate;

    public People() { }

    public People(String name, String email, String telephone, String documentNumber, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
    }
}
