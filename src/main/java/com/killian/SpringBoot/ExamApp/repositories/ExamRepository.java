package com.killian.SpringBoot.ExamApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.killian.SpringBoot.ExamApp.models.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Exam e WHERE e.name = :examName")
    boolean existsByExamName(String examName);

    @Query("SELECT DISTINCT e.subject FROM Exam e")
    List<String> findDistinctSubjects();

    @Query("SELECT e FROM Exam e WHERE e.subject = :subject")
    List<Exam> findBySubject(@Param("subject") String subject);

    @Query("SELECT e FROM Exam e WHERE e.subject = :subject AND e.examCode = :examCode")
    List<Exam> findBySubjectAndCode(@Param("subject") String subject, @Param("examCode") int examCode);

    @Query("SELECT e FROM Exam e WHERE e.name = :name AND e.owner = :owner")
    List<Exam> findByNameAndOwner(@Param("name") String name, @Param("owner") String owner);

    @Query("SELECT e FROM Exam e WHERE e.name = :name")
    List<Exam> findByName(@Param("name") String name);

    @Query("SELECT e FROM Exam e WHERE e.name = :name AND e.examCode = :examCode")
    Exam findByNameAndCode(@Param("name") String name, @Param("examCode") int examCode);

    @Query("SELECT DISTINCT e.examCode FROM Exam e WHERE e.name = :name AND e.owner = :owner")
    List<Integer> findDistinctExamCode(@Param("name") String name, @Param("owner") String owner);
}
