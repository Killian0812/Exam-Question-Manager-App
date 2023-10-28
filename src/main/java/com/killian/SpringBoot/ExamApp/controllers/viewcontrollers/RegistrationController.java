package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @GetMapping("/register-page")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("register.html");
        modelAndView.addObject("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("role") String role,
            @RequestParam("dob") String dob) {

        User user = userRepository.findByUsername(username);
        String message = null;
        if (user != null)
            message = "Tên người dùng đã được sử dụng. Hãy thử tên khác.";
        else if (email != null) {
            user = userRepository.findByEmail(email);
            if (user != null)
                message = "Email đã được sử dụng.";
            else {
                if (password.equals(confirmPassword) == false)
                    message = "Mật khẩu xác nhận không chính xác.";
                else {
                    message = "Đăng ký thành công. Hãy quay về trang đăng nhập.";
                    userRepository.save(new User(username, password, email, name, role, dob));
                }
            }
        }
        sessionManagementService.setMessage(message);
        return "redirect:/register-page";
    }
}
