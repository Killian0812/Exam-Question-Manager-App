package com.killian.SpringBoot.ExamApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.killian.SpringBoot.ExamApp.models.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("SELECT a FROM Assignment a WHERE a.className = :className")
    List<Assignment> findAssignmentsByClassname(@Param("className") String className);

    @Query("SELECT a FROM Assignment a WHERE a.className = :className AND a.name = :name")
    List<Assignment> findAssignmentByName(@Param("className") String className, @Param("name") String name);
}
