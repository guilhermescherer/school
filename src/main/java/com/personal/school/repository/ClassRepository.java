package com.personal.school.repository;

import com.personal.school.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
