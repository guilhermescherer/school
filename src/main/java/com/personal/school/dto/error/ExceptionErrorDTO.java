package com.personal.school.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class ExceptionErrorDTO {

    private static final int MAX_SIZE_TRACE = 5;

    private Integer code;
    private LocalDateTime date;
    private String message;
    @Setter(AccessLevel.NONE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> trace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorDTO> errors;

    public void setTrace(List<String> trace) {
        if(nonNull(trace) && trace.size() <= MAX_SIZE_TRACE) {
            this.trace = trace;
        }
    }
}
