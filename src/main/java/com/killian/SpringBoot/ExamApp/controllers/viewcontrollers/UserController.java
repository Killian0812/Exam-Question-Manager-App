package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.killian.SpringBoot.ExamApp.models.PasswordResetToken;
import com.killian.SpringBoot.ExamApp.models.User;
import com.killian.SpringBoot.ExamApp.repositories.PasswordResetTokenRepository;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;
import com.killian.SpringBoot.ExamApp.services.ImageStorageService;
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

    @Autowired
    private ImageStorageService storageService;

    @GetMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        boolean loginWithEmail = username.contains("@");
        User user = null;
        if (loginWithEmail)
            user = userRepository.findByEmail(username).orElse(null);
        else
            user = userRepository.findByUsername(username).orElse(null);
        String message = null;
        if (user == null) {
            message = loginWithEmail ? "Email chưa được đăng kí" : "Tên đăng nhập không tồn tại.";
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
        User user = userRepository.findByEmail(email).orElse(null);
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
        User user = userRepository.findByEmail(email).orElse(null);
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
        model.addAttribute("user", user);
        model.addAttribute("message", sessionManagementService.getMessage());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfilePage(Model model) {

        User user = userRepository.findByUsername(sessionManagementService.getUsername()).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "edit-profile";
    }

    @PostMapping("edit-profile")
    public String editProfile(
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("gender") String gender,
            @RequestParam("dob") String dob) {

        User thisUser = userRepository.findByUsername(sessionManagementService.getUsername()).get();
        User user = userRepository.findByEmail(email).get();
        if (user != null && user.getUsername() != thisUser.getUsername()) {
            sessionManagementService.setMessage("Email đã được đăng kí bởi tài khoản khác.");
            return "redirect:/profile/edit";
        }
        if (dob != null) {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dob, inputFormat);
            DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedDob = date.format(desiredFormat);
            thisUser.setDob(formattedDob);
        }
        if (email != null)
            thisUser.setEmail(email);
        if (gender != null)
            thisUser.setGender(gender);
        if (name != null)
            thisUser.setName(name);
        if (!avatar.isEmpty()) {
            String generatedFileName = storageService.storeFile(avatar);
            thisUser.setAvatarFileName(generatedFileName);
        }
        userRepository.save(thisUser);
        sessionManagementService.setMessage("Cập nhật thông tin thành công");
        return "redirect:/profile";
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
        User user = null;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username).orElse(null);
            sessionManagementService.setUsername(user.getUsername());
        } else
            user = userRepository.findByUsername(username).orElse(null);
        String role = sessionManagementService.getRole();

        // Use the data as needed
        model.addAttribute("name", user.getName());
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();

        return "teacher/teacher-dashboard";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model) {
        String username = sessionManagementService.getUsername();
        User user = null;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username).orElse(null);
            sessionManagementService.setUsername(user.getUsername());
        } else
            user = userRepository.findByUsername(username).orElse(null);
        String role = sessionManagementService.getRole();

        // Use the data as needed
        model.addAttribute("name", user.getName());
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
