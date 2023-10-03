package com.killian.SpringBoot.ExamApp.services;
// package com.killian.SpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Service
public class QuestionService {

    // private final Path storageFolder = Paths.get("uploads");

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(String text, List<String> choices, int correctAnswerID) {

        Question question = new Question(text, choices, correctAnswerID);

        return questionRepository.save(question);
    }
}
