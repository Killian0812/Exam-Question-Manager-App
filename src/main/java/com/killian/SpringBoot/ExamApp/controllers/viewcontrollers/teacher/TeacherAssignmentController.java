package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers.teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.Assignment;
import com.killian.SpringBoot.ExamApp.models.Classroom;
import com.killian.SpringBoot.ExamApp.repositories.AssignmentRepository;
import com.killian.SpringBoot.ExamApp.repositories.ClassroomRepository;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/teacher/classroom/assignment")
public class TeacherAssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping("")
    public String assignmentList(
            @RequestParam("classCode") String classCode,
            Model model) {
        Classroom classroom = classroomRepository.findByClasscode(classCode);
        List<Assignment> assignments = assignmentRepository.findAssignmentsByClasscode(classCode);
        model.addAttribute("assignments", assignments);
        model.addAttribute("classCode", classCode);
        model.addAttribute("className", classroom.getName());
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/assignments";
    }

    @GetMapping("add-assignment-page")
    public String addAssignmentPage(
            @RequestParam("classCode") String classCode,
            Model model) {
        String className = classroomRepository.findByClasscode(classCode).getName();
        model.addAttribute("classCode", classCode);
        model.addAttribute("className", className);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/add-assignment";
    }

    @PostMapping("add-assignment")
    public String addAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("deadline") String deadline,
            @RequestParam("examId") String examId,
            @RequestParam("classCode") String classCode) {

        Classroom classroom = classroomRepository.findByClasscode(classCode);
        String className = classroom.getName();
        if (assignmentRepository.findAssignmentByClasscodeAndName(classCode, assignmentName) == null) {
            if (examRepository.findByExamId(examId).isEmpty()) {
                sessionManagementService.setMessage("Không tìm thấy đề thi với ID đã cung cấp!");
                return "redirect:/teacher/classroom/assignment/add-assignment-page?classCode=" + classCode;
            }
            // 2023-10-27T13:43
            SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            String reformattedDeadline;
            try {
                reformattedDeadline = myFormat.format(fromUser.parse(deadline));
                Assignment newAssignment = new Assignment(assignmentName, reformattedDeadline, examId, className,
                        classCode);
                assignmentRepository.save(newAssignment);
                sessionManagementService.setMessage("Thêm bài tập thành công!");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "redirect:/teacher/classroom/assignment?classCode=" + classCode;
        } else {
            sessionManagementService.setMessage("Trùng tên bài tập!");
            return "redirect:/teacher/classroom/assignment/add-assignment-page?classCode=" + classCode;
        }
    }

    @PostMapping("remove-assignment")
    public String removeAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("classCode") String classCode) {
        Assignment assignment = assignmentRepository.findAssignmentByClasscodeAndName(classCode, assignmentName);
        assignmentRepository.delete(assignment);
        sessionManagementService.setMessage("Đã xóa bài tập: " + assignmentName);
        return "redirect:/teacher/classroom/assignment?classCode=" + classCode;
    }

    @GetMapping("view-assignment")
    public String viewAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("classCode") String classCode,
            Model model) {

        Assignment assignment = assignmentRepository.findAssignmentByClasscodeAndName(classCode, assignmentName);
        model.addAttribute("assignment", assignment);
        model.addAttribute("classCode", classCode);

        return "teacher/view-assignment";
    }
}
