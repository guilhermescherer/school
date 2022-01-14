package com.personal.school.model;

import com.personal.school.form.StudentForm;
import com.personal.school.service.ClassService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;

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

    public static Student toStudent(StudentForm studentForm, ClassService classService) {
        Class schoolClass = classService.getClassById(studentForm.getSchoolClass());
        LocalDate birthDate = LocalDate.parse(studentForm.getBirthDate(), getDefaultDateFormatter());

        return new Student(studentForm.getName(), studentForm.getEmail(), studentForm.getTelephone(),
                studentForm.getDocumentNumber(), birthDate, studentForm.getIsScholarshipHolder(), schoolClass);

    }
}
