package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createQuestionPage() {
        System.out.println("Question-creating page");
        return "create-question";
    }

    @PostMapping("/create-question")
    public ModelAndView createQuestion(
            @RequestParam("text") String text,
            @RequestParam("choices") List<String> choices,
            @RequestParam("correctAnswerID") int correctAnswerID,
            @RequestParam("subject") String subject,
            @RequestParam("difficulty") String difficulty) {

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
        
        return modelAndView;
    }

    @GetMapping("/view-questions-by-filter-page")
    public String getQuestionsByFilterPage(Model model) {
        System.out.println("Question-filtering page");

        List<String> subjects = questionRepository.findDistinctSubjects();
        List<String> difficulties = questionRepository.findDistinctDifficuties();

        model.addAttribute("subjects", subjects);
        model.addAttribute("difficulties", difficulties);

        // Initially, display questions from the first subject
        if (!subjects.isEmpty()) {
            List<Question> questions = questionRepository.findBySubjectAndDifficulty(subjects.get(0),
                    difficulties.get(0));
            model.addAttribute("selectedSubject", subjects.get(0));
            model.addAttribute("selectedDifficulty", difficulties.get(0));
            model.addAttribute("questions", questions);
        }

        return "questions-by-filter";
    }

    @GetMapping("/get-questions-by-subject-and-difficulty")
    public ModelAndView getQuestionsBySelectedSubjectAndDifficulty(
            @RequestParam("selectedSubject") String selectedSubject,
            @RequestParam("selectedDifficulty") String selectedDifficulty) {

        ModelAndView modelAndView = new ModelAndView("questions-by-filter.html");
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
