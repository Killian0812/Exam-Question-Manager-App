package com.killian.SpringBoot.ExamApp.services;

// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Service
public class ExamService {

    // private final Path storageFolder = Paths.get("uploads");

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    public Exam createExam(String examName, List<Long> questionIds) {
        
        Exam exam = new Exam();
        exam.setName(examName);

        List<Question> selectedQuestions = questionRepository.findAllById(questionIds);

        exam.setQuestions(selectedQuestions);

        return examRepository.save(exam);
    }

    public Exam getExamById(Long examId) {
        return examRepository.findById(examId).orElse(null);
    }
}
