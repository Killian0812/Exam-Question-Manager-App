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
            @RequestParam("className") String className,
            Model model) {
        Classroom classroom = classroomRepository.findByNameAndTeacher(className,
                sessionManagementService.getUsername());
        List<Assignment> assignments = assignmentRepository.findAssignmentsByClasscode(classroom.getClassCode());
        model.addAttribute("assignments", assignments);
        model.addAttribute("className", className);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/assignments";
    }

    @GetMapping("add-assignment-page")
    public String addAssignmentPage(
            @RequestParam("className") String className,
            Model model) {
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
            @RequestParam("className") String className) {

        Classroom classroom = classroomRepository.findByNameAndTeacher(className,
                sessionManagementService.getUsername());
        if (assignmentRepository.findAssignmentByNameAndClasscode(classroom.getClassCode(), assignmentName)
                .isEmpty()) {
            if (examRepository.findByExamId(examId).isEmpty()) {
                sessionManagementService.setMessage("Không tìm thấy đề thi với ID đã cung cấp!");
                return "redirect:/teacher/classroom/assignment/add-assignment-page?className=" + className;
            }
            // 2023-10-27T13:43
            SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            String reformattedDeadline;
            try {
                reformattedDeadline = myFormat.format(fromUser.parse(deadline));
                Assignment newAssignment = new Assignment(assignmentName, reformattedDeadline, examId, className,
                        classroom.getClassCode());
                assignmentRepository.save(newAssignment);
                sessionManagementService.setMessage("Thêm bài tập thành công!");
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        sessionManagementService.setMessage("Đã xóa bài tập: " + assignmentName);
        return "redirect:/teacher/classroom/assignment?className=" + className;
    }

    @GetMapping("view-assignment")
    public String viewAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("className") String className,
            Model model) {
        Assignment assignment = assignmentRepository.findAssignmentByName(className, assignmentName).get(0);
        model.addAttribute("assignment", assignment);

        // 2023-10-27T13:43
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        try {
            String reformattedDeadline = myFormat.format(fromUser.parse(assignment.getDeadline()));
            model.addAttribute("assignmentDeadline", reformattedDeadline);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("className", className);

        return "teacher/view-assignment";
    }
}
