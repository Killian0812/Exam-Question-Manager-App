package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.Assignment;
import com.killian.SpringBoot.ExamApp.repositories.AssignmentRepository;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/teacher/classroom/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @GetMapping("")
    public String assignmentList(
            @RequestParam("className") String className,
            Model model) {
        List<Assignment> assignments = assignmentRepository.findAssignmentsByClassname(className);
        model.addAttribute("assignments", assignments);
        model.addAttribute("className", className);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "assignments";
    }

    @GetMapping("add-assignment-page")
    public String addAssignmentPage(
            @RequestParam("className") String className,
            Model model) {
        model.addAttribute("className", className);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "add-assignment";
    }

    @PostMapping("add-assignment")
    public String addAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("deadline") String deadline,
            @RequestParam("examId") String examId,
            @RequestParam("className") String className) {

        if (assignmentRepository.findAssignmentByName(className, assignmentName)
                .isEmpty()) {
            if (examRepository.findByExamId(examId).isEmpty()) {
                sessionManagementService.setMessage("Không tìm thấy đề thi với ID đã cung cấp!");
                return "redirect:/teacher/classroom/assignment/add-assignment-page?className=" + className;
            }
            Assignment newAssignment = new Assignment(assignmentName, deadline, examId, className);
            assignmentRepository.save(newAssignment);
            sessionManagementService.setMessage("Thêm bài tập thành công!");
            return "redirect:/teacher/classroom/assignment?className=" + className;
        } else {
            sessionManagementService.setMessage("Trùng tên bài tập!");
            return "redirect:/teacher/classroom/assignment/add-assignment-page?className=" + className;
        }
    }

    @PostMapping("remove-assignment")
    public String removeAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("className") String className) {
        List<Assignment> assignments = assignmentRepository.findAssignmentByName(className, assignmentName);
        assignmentRepository.deleteAll(assignments);
        return "redirect:/teacher/classroom/assignment?className=" + className;
    }
}
