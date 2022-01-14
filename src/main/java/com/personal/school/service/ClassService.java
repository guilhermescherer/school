package com.personal.school.service;

import com.personal.school.model.Class;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassService {

    List<Class> getAllById(List<Long> idList);

    Class getClassById(Long idClass);

}
