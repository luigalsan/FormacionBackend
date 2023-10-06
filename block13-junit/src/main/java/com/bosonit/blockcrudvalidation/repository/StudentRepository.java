package com.bosonit.blockcrudvalidation.repository;


import com.bosonit.blockcrudvalidation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer> {
}
