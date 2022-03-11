package com.personal.school.validator;

import com.personal.school.form.UpdateSalaryForm;
import com.personal.school.model.Teacher;

public abstract class AbstractUpdateSalary {

    private AbstractUpdateSalary next;

    public AbstractUpdateSalary linkWith(AbstractUpdateSalary next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Teacher teacher, UpdateSalaryForm form);

    protected boolean checkNext(Teacher teacher, UpdateSalaryForm form) {
        if(next == null) {
            return true;
        }

        return next.check(teacher, form);
    }
}
