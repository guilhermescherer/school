package com.personal.school.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArgumentNotValidDTO {

    private String field;
    private String error;

    public ArgumentNotValidDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

}
