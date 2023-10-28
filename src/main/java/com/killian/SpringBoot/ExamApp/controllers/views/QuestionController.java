package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
public class QuestionController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private QuestionRepository questionRepository;

    private String message;

    @GetMapping("/create-question-page")
    public String createQuestionPage(Model model) {
        String username = sessionManagementService.getUsername();
        String password = sessionManagementService.getPassword();
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("message", message);
        message = null;
        return "teacher/create-question";
    }

    @PostMapping("/create-question")
    public String createQuestion(
            @RequestParam("text") String text,
            @RequestParam("choices") List<String> choices,
            @RequestParam("answer") String answer,
            @RequestParam("subject") String subject,
            @RequestParam("difficulty") String difficulty,
            @RequestParam("chapter") String chapter,
            @RequestParam("questionType") String questionType,
            Model model) {

        if (!choices.isEmpty()) {
            choices.remove(0);
        }
        
        Question newQuestion = new Question();
        newQuestion.setText(text);
        newQuestion.setChoices(choices);
        newQuestion.setAnswer(answer);
        newQuestion.setSubject(subject);
        newQuestion.setDifficulty(difficulty);
        newQuestion.setChapter(chapter);
        newQuestion.setQuestionType(questionType);

        if (questionType.equals("multiple-choice") && choices.size() < 2)
            message = "Number of choices must be >= 2";
        else
            try {
                questionRepository.save(newQuestion);
                message = "Successful! Question added to database.";
            } catch (Exception e) {
                message = "Failed! Question is already in database.";
            }
        return "redirect:/create-question-page";
    }

    @GetMapping("/view-questions-by-filter-page")
    public String getQuestionsByFilterPage(Model model) {

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

        return "teacher/questions-by-filter";
    }

    @GetMapping("/get-questions-by-subject-and-difficulty")
    public String getQuestionsBySelectedSubjectAndDifficulty(
            @RequestParam("selectedSubject") String selectedSubject,
            @RequestParam("selectedDifficulty") String selectedDifficulty,
            Model model) {

        // Retrieve questions based on the selected subject and difficulty
        List<Question> questions = questionRepository.findBySubjectAndDifficulty(selectedSubject, selectedDifficulty);

        List<String> subjects = questionRepository.findDistinctSubjects();
        List<String> difficulties = questionRepository.findDistinctDifficuties();

        model.addAttribute("subjects", subjects);
        model.addAttribute("difficulties", difficulties);

        // Retrieve all filtered questions
        model.addAttribute("selectedSubject", selectedSubject);
        model.addAttribute("selectedDifficulty", selectedDifficulty);
        model.addAttribute("questions", questions);

        return "teacher/questions-by-filter.html";
    }
}
