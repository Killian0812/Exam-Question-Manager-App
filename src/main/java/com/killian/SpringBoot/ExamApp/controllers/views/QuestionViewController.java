package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Controller
public class QuestionViewController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/create-question-page")
    public ModelAndView createQuestionPage(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("create-question.html");
        modelAndView.addObject("username", username);
        modelAndView.addObject("password", password);
        return modelAndView;
    }

    @PostMapping("/create-question")
    public ModelAndView createQuestion(
            @RequestParam("text") String text,
            @RequestParam("choices") List<String> choices,
            @RequestParam("correctAnswerID") int correctAnswerID,
            @RequestParam("subject") String subject,
            @RequestParam("difficulty") String difficulty,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView("create-question.html");

        Question newQuestion = new Question();
        newQuestion.setText(text);
        newQuestion.setChoices(choices);
        newQuestion.setCorrectAnswerID(correctAnswerID - 1);
        newQuestion.setSubject(subject);
        newQuestion.setDifficulty(difficulty);

        String message = null;
        try {
            questionRepository.save(newQuestion);
            message = "Successful! Question added to database.";
        } catch (Exception e) {
            message = "Failed! Question is already in database.";
        }
        modelAndView.addObject("message", message);
        modelAndView.addObject("password", password);
        modelAndView.addObject("username", username);
        return modelAndView;
    }

    @GetMapping("/view-questions-by-filter-page")
    public ModelAndView getQuestionsByFilterPage(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView("questions-by-filter.html");
        modelAndView.addObject("password", password);
        modelAndView.addObject("username", username);

        List<String> subjects = questionRepository.findDistinctSubjects();
        List<String> difficulties = questionRepository.findDistinctDifficuties();

        modelAndView.addObject("subjects", subjects);
        modelAndView.addObject("difficulties", difficulties);

        // Initially, display questions from the first subject
        if (!subjects.isEmpty()) {
            List<Question> questions = questionRepository.findBySubjectAndDifficulty(subjects.get(0),
                    difficulties.get(0));
            modelAndView.addObject("selectedSubject", subjects.get(0));
            modelAndView.addObject("selectedDifficulty", difficulties.get(0));
            modelAndView.addObject("questions", questions);
        }

        return modelAndView;
    }

    @GetMapping("/get-questions-by-subject-and-difficulty")
    public ModelAndView getQuestionsBySelectedSubjectAndDifficulty(
            @RequestParam("selectedSubject") String selectedSubject,
            @RequestParam("selectedDifficulty") String selectedDifficulty,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView("questions-by-filter.html");
        modelAndView.addObject("password", password);
        modelAndView.addObject("username", username);
        
        // Retrieve questions based on the selected subject and difficulty
        List<Question> questions = questionRepository.findBySubjectAndDifficulty(selectedSubject, selectedDifficulty);

        List<String> subjects = questionRepository.findDistinctSubjects();
        List<String> difficulties = questionRepository.findDistinctDifficuties();

        modelAndView.addObject("subjects", subjects);
        modelAndView.addObject("difficulties", difficulties);

        // Retrieve all filtered questions
        modelAndView.addObject("selectedSubject", selectedSubject);
        modelAndView.addObject("selectedDifficulty", selectedDifficulty);
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }
}
