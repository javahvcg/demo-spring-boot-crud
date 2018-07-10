package com.example.model;

import com.example.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentModel extends CrudRepository<Student, Integer> {
}
