package com.killian.SpringBoot.ExamApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.killian.SpringBoot.ExamApp.models.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("SELECT s FROM Submission s WHERE s.assignmentId = :assignmentId AND s.student = :student")
    Submission findByAssignmentId(@Param("assignmentId") String assignmentId, @Param("student") String student);

    @Query("SELECT s FROM Submission s WHERE s.submissionId = :submissionId")
    Submission findBySubmissionId(@Param("submissionId") String submissionId);
}
