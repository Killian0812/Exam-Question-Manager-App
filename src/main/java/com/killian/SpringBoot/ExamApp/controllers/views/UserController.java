package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        User user = userRepository.findByUsername(username);
        String message = null;

        if (user == null) {
            message = "Invalid username";
            model.addAttribute("message", message);
            return "login";
        } else {
            if (password.equals(user.getPassword()) == false) {
                message = "Password incorrect";
                model.addAttribute("message", message);
                return "login";
            } else {
                sessionManagementService.createUserSession(username, password, user.getRole());
                return "redirect:/user/dashboard";
            }
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Retrieve user data from the session
        String username = sessionManagementService.getUsername();
        String password = sessionManagementService.getPassword();
        String role = sessionManagementService.getRole();

        // Use the data as needed
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("role", role);

        if (role.equals("Teacher"))
            return "teacher-dashboard";
        else
            return "student-dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        // Clear user session on logout
        sessionManagementService.clearUserSession();
        return "redirect:/";
    }
}
