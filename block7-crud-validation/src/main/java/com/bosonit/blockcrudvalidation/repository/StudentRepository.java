package com.bosonit.block7crudvalidation.repository;


import com.bosonit.block7crudvalidation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer> {
}
