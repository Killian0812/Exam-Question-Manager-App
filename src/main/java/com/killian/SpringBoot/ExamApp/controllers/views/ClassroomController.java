package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.Classroom;
import com.killian.SpringBoot.ExamApp.models.StudentClassroom;
import com.killian.SpringBoot.ExamApp.repositories.ClassroomRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;
import com.killian.SpringBoot.ExamApp.repositories.StudentClassroomRepository;
import com.killian.SpringBoot.ExamApp.repositories.UserRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/teacher/classroom")
public class ClassroomController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/classrooms-page")
    public String classroomPage(Model model) {
        List<Classroom> classrooms = classroomRepository.findByTeacher(sessionManagementService.getUsername());
        List<String> classNames = classrooms.stream()
                .map(Classroom::getName)
                .collect(Collectors.toList());
        model.addAttribute("classNames", classNames);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "classrooms";
    }

    @GetMapping("/view-classroom")
    public String viewClassroom(
            @RequestParam("className") String className,
            Model model) {
        Classroom classroom = classroomRepository.findByNameAndTeacher(className,
                sessionManagementService.getUsername());
        model.addAttribute("classroom", classroom);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "view-classroom";
    }

    @PostMapping("/remove-classroom")
    public String removeClassroom(
            @RequestParam("className") String className) {
        List<StudentClassroom> studentClassrooms = studentClassroomRepository.findAllRecordByClass(className);
        studentClassroomRepository.deleteAll(studentClassrooms);
        Classroom classroom = classroomRepository.findByName(className);
        classroomRepository.delete(classroom);
        sessionManagementService.setMessage("Bạn đã xóa lớp " + className);
        return "redirect:/teacher/classroom/classrooms-page";
    }

    @GetMapping("/student-list")
    public String studentList(
            @RequestParam("className") String className,
            Model model) {
        List<String> students = studentClassroomRepository.findAllStudentsByClassname(className);
        // Classroom classroom = classroomRepository.findByName(className);
        model.addAttribute("className", className);
        model.addAttribute("students", students);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "student-list";
    }

    @PostMapping("/add-student")
    public String addStudent(
            @RequestParam("student") String student,
            @RequestParam("className") String className) {
        List<String> students = studentClassroomRepository.findAllStudentsByClassname(className);
        if (students.contains(student)) {
            sessionManagementService.setMessage("Học sinh hiện đã được thêm vào lớp");
        } else {
            if (userRepository.existsByUsername(student)) {
                sessionManagementService.setMessage("Thêm học sinh thành công");
                studentClassroomRepository
                        .save(new StudentClassroom(student, className, classroomRepository.classCodeByName(className)));
            } else {
                sessionManagementService.setMessage("Không tồn tại người dùng!");
            }
        }
        return "redirect:/teacher/classroom/student-list?className=" + className;
    }

    @PostMapping("/remove-student")
    public String removeStudent(
            @RequestParam("name") String name,
            @RequestParam("className") String className) {
        StudentClassroom studentClassroom = studentClassroomRepository.findRecord(name, className);
        studentClassroomRepository.delete(studentClassroom);
        return "redirect:/teacher/classroom/student-list?className=" + className;
    }

    @GetMapping("/create-classroom-page")
    public String createClassroomPage(Model model) {
        List<String> subjects = questionRepository.findDistinctSubjects();
        List<Integer> grades = questionRepository.findDistinctGrades();
        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "create-classroom";
    }

    @PostMapping("/create-classroom")
    public String createClassroom(
            @RequestParam String name,
            @RequestParam String selectedSubject,
            @RequestParam String selectedGrade,
            Model model) {
        String owner = sessionManagementService.getUsername();
        Classroom newClassroom = new Classroom(name, selectedSubject, selectedGrade,
                owner);
        Classroom classroom = classroomRepository.findByNameAndTeacher(name, owner);
        if (classroom == null) {
            classroomRepository.save(newClassroom);
            sessionManagementService.setMessage("Tạo lớp thành công");
            return "redirect:/teacher/classroom/view-classroom?className=" + name;
        } else {
            sessionManagementService.setMessage("Tên lớp đã tồn tại");
            return "redirect:/teacher/classroom/create-classroom-page";
        }
    }
}
