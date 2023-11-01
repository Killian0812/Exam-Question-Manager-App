package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers.teacher;

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
public class TeacherClassroomController {

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
        List<String> classCodes = classrooms.stream()
                .map(Classroom::getClassCode)
                .collect(Collectors.toList());
        model.addAttribute("classNames", classNames);
        model.addAttribute("classCodes", classCodes);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/classrooms";
    }

    @GetMapping("/view-classroom")
    public String viewClassroom(
            @RequestParam("classCode") String classCode,
            Model model) {
        Classroom classroom = classroomRepository.findByClasscode(classCode);
        model.addAttribute("classroom", classroom);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/view-classroom";
    }

    @PostMapping("/remove-classroom")
    public String removeClassroom(
            @RequestParam("classCode") String classCode) {
        List<StudentClassroom> studentClassrooms = studentClassroomRepository.findAllRecordByClasscode(classCode);
        studentClassroomRepository.deleteAll(studentClassrooms);
        Classroom classroom = classroomRepository.findByClasscode(classCode);
        sessionManagementService.setMessage("Bạn đã xóa lớp " + classroom.getName());
        classroomRepository.delete(classroom);
        return "redirect:/teacher/classroom/classrooms-page";
    }

    @GetMapping("/student-list")
    public String studentList(
            @RequestParam("classCode") String classCode,
            Model model) {
        List<String> students = studentClassroomRepository.findAllStudentsByClasscode(classCode);
        String className = classroomRepository.findByClasscode(classCode).getName();
        model.addAttribute("className", className);
        model.addAttribute("classCode", classCode);
        model.addAttribute("students", students);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/student-list";
    }

    @PostMapping("/add-student")
    public String addStudent(
            @RequestParam("student") String student,
            @RequestParam("classCode") String classCode) {
        List<String> students = studentClassroomRepository.findAllStudentsByClasscode(classCode);
        String className = classroomRepository.findByClasscode(classCode).getName();
        if (students.contains(student)) {
            sessionManagementService.setMessage("Học sinh hiện đã ở trong lớp");
        } else {
            if (userRepository.existsByUsername(student)) {
                sessionManagementService.setMessage("Thêm học sinh thành công");
                studentClassroomRepository
                        .save(new StudentClassroom(student, className, classCode));
            } else {
                sessionManagementService.setMessage("Không tồn tại người dùng!");
            }
        }
        return "redirect:/teacher/classroom/student-list?classCode=" + classCode;
    }

    @PostMapping("/remove-student")
    public String removeStudent(
            @RequestParam("name") String name,
            @RequestParam("classCode") String classCode) {
        StudentClassroom studentClassroom = studentClassroomRepository.findRecord(name, classCode);
        studentClassroomRepository.delete(studentClassroom);
        return "redirect:/teacher/classroom/student-list?classCode=" + classCode;
    }

    @GetMapping("/create-classroom-page")
    public String createClassroomPage(Model model) {
        List<String> subjects = questionRepository.findDistinctSubjects();
        List<Integer> grades = questionRepository.findDistinctGrades();
        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/create-classroom";
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
            return "redirect:/teacher/classroom/view-classroom?classCode=" + newClassroom.getClassCode();
        } else {
            sessionManagementService.setMessage("Tên lớp đã tồn tại");
            return "redirect:/teacher/classroom/create-classroom-page";
        }
    }
}
