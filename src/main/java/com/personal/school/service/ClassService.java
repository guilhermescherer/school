package com.personal.school.service;

import com.personal.school.model.Class;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {

    List<Class> getClassesByListId(List<Long> idList);

    Class getClassById(Long idClass);
}
