package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Controller
public class QuestionViewController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/create-question")
    public String showCreateView() {
        return "create-question";
    }

    @PostMapping("/create-question/new")
    public String createQuestion(
            @RequestParam("text") String questionText,
            @RequestParam("choices") List<String> choices,
            @RequestParam("correctAnswerID") int correctAnswerID,
            RedirectAttributes redirectAttributes) {

        Question newQuestion = new Question();
        newQuestion.setText(questionText);
        newQuestion.setChoices(choices);
        newQuestion.setCorrectAnswerID(correctAnswerID - 1);

        String msg = null;
        try {
            questionRepository.save(newQuestion);
            msg = "Question added to database";
        } catch (Exception e) {
            msg = "Question duplicated";
        }
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/";
    }

    // @GetMapping("/all-question")
    // public ModelAndView allQuestionView() {

    // }
}
