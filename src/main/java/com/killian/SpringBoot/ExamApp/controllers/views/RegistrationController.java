package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register-page")
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("role") String role) {

        ModelAndView modelAndView = new ModelAndView("register.html");

        User user = userRepository.findByUsername(username);
        String message = null;
        if (user != null)
            message = "Failed. Username taken.";
        else if (email != null) {
            user = userRepository.findByEmail(email);
            if (user != null)
                message = "Failed. Email already taken.";
        }
        if (password.equals(confirmPassword) == false)
            message = "Failed! Confirm Password not matching.";
        else {
            message = "Registration successful!. Please go to login page.";
            userRepository.save(new User(username, password, email, role));
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
