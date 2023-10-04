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
        System.out.print("Question-creating page");
        return "create-question";
    }
    
    @PostMapping("/create-question")
    public ModelAndView createQuestion(
            @RequestParam("text") String text,
            @RequestParam("choices") List<String> choices,
            @RequestParam("correctAnswerID") int correctAnswerID,
            @RequestParam("category") String category,
            @RequestParam("difficulty") String difficulty) {

        ModelAndView modelAndView = new ModelAndView("create-question.html");

        Question newQuestion = new Question();
        newQuestion.setText(text);
        newQuestion.setChoices(choices);
        newQuestion.setCorrectAnswerID(correctAnswerID - 1);
        newQuestion.setCategory(category);
        newQuestion.setDifficulty(difficulty);

        String message = null;
        try {
            questionRepository.save(newQuestion);
            message = "Success! Question added to database.";
        } catch (Exception e) {
            message = "Failed! Question is already in database.";
        }
        
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/questions-by-filter-page")
    public String getQuestionsByFilterPage(Model model) {
        System.out.println("Question-filtering page");

        List<String> categories = questionRepository.findDistinctCategories();
        List<String> difficulties = questionRepository.findDistinctDifficuties();

        model.addAttribute("categories", categories);
        model.addAttribute("difficulties", difficulties);

        // Initially, display questions from the first category
        if (!categories.isEmpty()) {
            List<Question> questions = questionRepository.findByCategory(categories.get(0));
            model.addAttribute("selectedCategory", categories.get(0));
            model.addAttribute("selectedDifficulty", difficulties.get(0));
            model.addAttribute("questions", questions);
        }

        return "questions-by-filter";
    }

    @GetMapping("/get-questions-by-category-and-difficulty")
    public ModelAndView getQuestionsBySelectedCategory(
            @RequestParam("selectedCategory") String selectedCategory,
            @RequestParam("selectedDifficulty") String selectedDifficulty) {

        ModelAndView modelAndView = new ModelAndView("questions-by-filter.html");
        // Retrieve questions based on the selected category and difficulty
        List<Question> questions = questionRepository.findByCategoryAndDifficulty(selectedCategory, selectedDifficulty);

        List<String> categories = questionRepository.findDistinctCategories();
        List<String> difficulties = questionRepository.findDistinctDifficuties();

        modelAndView.addObject("categories", categories);
        modelAndView.addObject("difficulties", difficulties);

        // Retrieve all filtered questions
        modelAndView.addObject("selectedCategory", selectedCategory);
        modelAndView.addObject("selectedDifficulty", selectedDifficulty);
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }
}
