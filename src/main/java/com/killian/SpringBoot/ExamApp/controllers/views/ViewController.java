package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
public class ViewController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login.html");
        modelAndView.addObject("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return modelAndView;
    }

    @GetMapping("/forget-password")
    public ModelAndView forget() {
        ModelAndView modelAndView = new ModelAndView("forget-password.html");
        modelAndView.addObject("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return modelAndView;
    } 

}
