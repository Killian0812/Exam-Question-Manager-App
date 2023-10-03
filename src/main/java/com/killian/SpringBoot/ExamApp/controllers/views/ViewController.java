package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/")
    public ModelAndView index() {
        System.out.println("Main page");
        ModelAndView modelAndView = new ModelAndView("index.html");
        return modelAndView;
    }

    @GetMapping("/create-question-view")
    public ModelAndView createQuestionView() {
        System.out.println("Create question page");
        ModelAndView modelAndView = new ModelAndView("create-question.html");
        return modelAndView;
    }

    // @GetMapping("/all-question")
    // public ModelAndView allQuestionView() {

    // }
}
