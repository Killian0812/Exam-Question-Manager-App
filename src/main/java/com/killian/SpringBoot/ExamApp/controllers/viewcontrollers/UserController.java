package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.PasswordResetToken;
import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.PasswordResetTokenRepository;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;
import com.killian.SpringBoot.ExamApp.services.UserServiceImpl;

@Controller
public class UserController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @GetMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        User user = userRepository.findByUsername(username).orElse(null);
        String message = null;

        if (user == null) {
            message = "Tên đăng nhập không tồn tại.";
            sessionManagementService.setMessage(message);
            return "redirect:/";
        } else {
            if (!userService.correctPassword(user, password)) {
                message = "Sai mật khẩu.";
                sessionManagementService.setMessage(message);
                sessionManagementService.setUsername(username);
                return "redirect:/";
            } else {
                sessionManagementService.createUserSession(username, user.getRole());
                return "redirect:/" + user.getRole().toLowerCase() + "/dashboard";
            }
        }
    }

    @GetMapping("/reset-password-page")
    public String resetPasswordPage(@RequestParam("tokenId") String tokenId, Model model) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByTokenId(tokenId);
        String email = passwordResetToken.getEmail();
        User user = userRepository.findByEmail(email);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", email);
        model.addAttribute("tokenId", tokenId);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam("tokenId") String tokenId,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {
        User user = userRepository.findByEmail(email);
        if (!password.equals(confirmPassword)) {
            sessionManagementService.setMessage("Mật khẩu xác nhận không chính xác.");
            return "redirect:/reset-password-page?tokenId=" + tokenId;
        }
        userService.changePassword(user, confirmPassword);
        sessionManagementService.setMessage("Đổi mật khẩu thành công");
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        User user = userRepository.findByUsername(sessionManagementService.getUsername()).orElse(null);
        ;
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/change-password-page")
    public String changePasswordPage(Model model) {
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {
        User user = userRepository.findByUsername(sessionManagementService.getUsername()).get();
        if (userService.correctPassword(user, currentPassword)) {
            if (newPassword.equals(confirmPassword)) {
                sessionManagementService.setMessage("Đổi mật khẩu thành công.");
                userService.changePassword(user, newPassword);
            } else {
                sessionManagementService.setMessage("Mật khẩu xác nhận không đúng.");
            }
        } else
            sessionManagementService.setMessage("Mật khẩu hiện tại không đúng");
        return "redirect:/change-password-page";
    }

    @GetMapping("/back-to-dashboard")
    public String backToDashboard() {
        return "redirect:/" + sessionManagementService.getRole().toLowerCase() + "/dashboard";
    }

    @GetMapping("/teacher/dashboard")
    public String teacherDashboard(Model model) {
        // Retrieve user data from the session
        String username = sessionManagementService.getUsername();
        String role = sessionManagementService.getRole();

        // Use the data as needed
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();

        return "teacher/teacher-dashboard";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model) {
        String username = sessionManagementService.getUsername();
        String role = sessionManagementService.getRole();

        // Use the data as needed
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();

        return "student/student-dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        // Clear user session on logout
        sessionManagementService.clearUserSession();
        return "redirect:/";
    }

    // @GetMapping("/401")
    // public String unauthorized() {
    // return "error";
    // }

    // @GetMapping("/403")
    // public String forbidden() {
    // return "error";
    // }

    @GetMapping("/404")
    public String notFound() {
        return "error";
    }
}
