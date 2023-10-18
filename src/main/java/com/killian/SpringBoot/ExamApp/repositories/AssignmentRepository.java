package com.killian.SpringBoot.ExamApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.killian.SpringBoot.ExamApp.models.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    
}
