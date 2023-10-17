package com.killian.SpringBoot.ExamApp.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;
import com.killian.SpringBoot.ExamApp.services.EmailService;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        User user = userRepository.findByUsername(username);
        String message = null;

        if (user == null) {
            message = "Tên đăng nhập không tồn tại.";
            sessionManagementService.setMessage(message);
            return "redirect:/";
        } else {
            if (password.equals(user.getPassword()) == false) {
                message = "Sai mật khẩu.";
                sessionManagementService.setMessage(message);
                return "redirect:/";
            } else {
                sessionManagementService.createUserSession(username, password, user.getRole());
                return "redirect:/user/dashboard";
            }
        }
    }

    @PostMapping("/forget")
    public String forget(@RequestParam("email") String email, Model model) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            sessionManagementService.setMessage("Email chưa đăng ký tài khoản.");
            return "redirect:/forget-password";
        }
        try {
            emailService.sendEmail(email, "Quên mật khẩu", "Tên đăng nhập");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/forget-password";
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
        model.addAttribute("message", sessionManagementService.getMessage());

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
