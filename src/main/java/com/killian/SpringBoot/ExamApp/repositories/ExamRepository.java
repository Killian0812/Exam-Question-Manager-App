package com.killian.SpringBoot.ExamApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.killian.SpringBoot.ExamApp.models.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Exam e WHERE e.name = :examName")
    boolean existsByExamName(String examName);
}
