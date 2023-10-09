package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }

    @GetMapping("/home-page")
    public ModelAndView homePage(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        User user = userRepository.findByUsername(username);
        ModelAndView modelAndView = new ModelAndView("login.html");
        String message = null;
        if (user != null) {
            if (password.equals(user.getPassword()) == false) {
                message = "Incorrect password!";
                modelAndView.addObject("message", message);
                return modelAndView;
            } else {
                modelAndView = new ModelAndView("home-page.html");
                message = "Login successful!";
                String role = user.getRole();
                modelAndView.addObject("username", username);
                modelAndView.addObject("password", password);
                modelAndView.addObject("role", role);
                modelAndView.addObject("message", message);
                return modelAndView;
            }
        } else {
            message = "Invalid username!";
            modelAndView.addObject("message", message);
            return modelAndView;
        }
    }

    @GetMapping("/main")
    public ModelAndView index() {
        System.out.println("Main page");
        ModelAndView modelAndView = new ModelAndView("index.html");
        return modelAndView;
    }

}
