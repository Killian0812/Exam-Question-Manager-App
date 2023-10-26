package com.killian.SpringBoot.ExamApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.killian.SpringBoot.ExamApp.models.StudentClassroom;

public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {
    @Query("SELECT DISTINCT sc.student FROM StudentClassroom sc WHERE sc.className = :className")
    List<String> findAllStudentsByClassname(@Param("className") String className);

    @Query("SELECT sc FROM StudentClassroom sc WHERE sc.className = :className AND sc.student = :student")
    StudentClassroom findRecord(@Param("student") String student, @Param("className") String className);

    @Query("SELECT sc FROM StudentClassroom sc WHERE sc.className = :className")
    List<StudentClassroom> findAllRecordByClass(@Param("className") String className);
}
