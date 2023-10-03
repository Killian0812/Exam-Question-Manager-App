package com.killian.SpringBoot.ExamApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.killian.SpringBoot.ExamApp.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByText(String text);
}
