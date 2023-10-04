package com.killian.SpringBoot.ExamApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.killian.SpringBoot.ExamApp.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByText(String text);

    @Query("SELECT DISTINCT q.category FROM Question q")
    List<String> findDistinctCategories();

    @Query("SELECT DISTINCT q.difficulty FROM Question q")
    List<String> findDistinctDifficuties();

    @Query("SELECT q FROM Question q WHERE q.category = :category")
    List<Question> findByCategory(@Param("category") String category);

    @Query("SELECT q FROM Question q WHERE q.category = :category AND q.difficulty = :difficulty")
    List<Question> findByCategoryAndDifficulty(@Param("category") String category,
            @Param("difficulty") String difficulty);
}
