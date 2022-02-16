package com.personal.school.model;

import com.personal.school.enums.UpdateSalaryType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.personal.school.utils.CalcUtils.getValueWithPercentage;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
public class Teacher extends People {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Setter(AccessLevel.NONE)
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

    public Teacher(String name, String email, String telephone, String cpf, LocalDate birthDate, BigDecimal salary, Schooling schooling) {
        super(name, email, telephone, cpf, birthDate);
        this.salary = salary;
        this.schooling = schooling;
    }

    public void setSalary(BigDecimal salary) {
        if(isNull(this.salary)) {
            this.salary = salary;
        } else {
            throw new RuntimeException("The salary already exists, it can only be readjusted");
        }
    }

    public void updateSalary(String value, UpdateSalaryType updateSalaryType) {
        if(nonNull(this.salary)) {
            switch (updateSalaryType) {
                case PERCENTAGE:
                    this.salary = getValueWithPercentage(this.salary, new BigDecimal(value));
                    break;
                case SUM:
                    this.salary = this.salary.add(new BigDecimal(value));
                    break;
            }
        }
    }
}
