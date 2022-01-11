package com.personal.school.model;

import com.personal.school.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student extends People{

    private Boolean isScholarshipHolder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = false)
    private Class schoolClass;

}
