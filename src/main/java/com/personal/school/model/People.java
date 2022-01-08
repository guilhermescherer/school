package com.personal.school.model;

import com.personal.school.form.TeacherForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

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

    public People(TeacherForm teacherForm){
        this.name = teacherForm.getName();
        this.email = teacherForm.getEmail();
        this.telephone = teacherForm.getTelephone();
        this.documentNumber = teacherForm.getDocumentNumber();
        LocalDate date = LocalDate.parse(teacherForm.getBirthDate(), getDefaultDateFormatter());
        this.birthDate = date;
    }
}
