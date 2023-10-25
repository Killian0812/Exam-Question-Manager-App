package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
public class LoginController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("message", sessionManagementService.getMessage());
        String username = sessionManagementService.getUsername();
        model.addAttribute("lastUsername", username);
        sessionManagementService.clearUsername();
        sessionManagementService.clearMessage();
        return "login";
    }

    @GetMapping("/forget-password")
    public ModelAndView forget() {
        ModelAndView modelAndView = new ModelAndView("forget-password.html");
        modelAndView.addObject("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return modelAndView;
    } 

}
