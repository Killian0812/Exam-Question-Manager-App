package com.killian.SpringBoot.ExamApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionManagementService {

    private final HttpSession httpSession;

    @Autowired
    public SessionManagementService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void createUserSession(String username, String password, String role) {
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("password", password);
        httpSession.setAttribute("role", role);
    }

    public void clearUserSession() {
        httpSession.invalidate();
    }

    public String getUsername() {
        return (String) httpSession.getAttribute("username");
    }

    public String getPassword() {
        return (String) httpSession.getAttribute("password");
    }

    public String getRole() {
        return (String) httpSession.getAttribute("role");
    }
}
