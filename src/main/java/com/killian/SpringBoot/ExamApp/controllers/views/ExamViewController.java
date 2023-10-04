package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Controller
public class ExamViewController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/create-exam-page")
    public String createQuestionPage(Model model) {
        System.out.println("Exam-creating page");
        List<String> subjects = questionRepository.findDistinctSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("selectedSubject", subjects.get(0));
        return "create-exam";
    }

    @PostMapping("/create-exam")
    public ModelAndView createQuestion(
            @RequestParam("name") String name,
            @RequestParam("selectedSubject") String subject,
            @RequestParam("easyQuestionCount") int easyQuestionCount,
            @RequestParam("mediumQuestionCount") int mediumQuestionCount,
            @RequestParam("hardQuestionCount") int hardQuestionCount) {

        ModelAndView modelAndView = new ModelAndView("create-exam.html");

        Exam newExam = new Exam();
        newExam.setName(name);
        newExam.setSubject(subject);

        List<Question> easyQuestions = questionRepository.findRandomEasyQuestions(easyQuestionCount);
        List<Question> mediumQuestions = questionRepository.findRandomMediumQuestions(mediumQuestionCount);
        List<Question> hardQuestions = questionRepository.findRandomHardQuestions(hardQuestionCount);

        List<Question> questions = Stream.of(easyQuestions, mediumQuestions, hardQuestions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        newExam.setQuestions(questions);

        String message = null;
        try {
            examRepository.save(newExam);
            message = "Successful! Exam added to database.";
        } catch (Exception e) {
            message = "Failed! Exam is already in database.";
        }
        modelAndView.addObject("message", message);

        return modelAndView;
    }

}
