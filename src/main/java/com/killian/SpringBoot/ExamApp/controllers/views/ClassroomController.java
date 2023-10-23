package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.Classroom;
import com.killian.SpringBoot.ExamApp.repositories.ClassroomRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/user/classroom")
public class ClassroomController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping("/classrooms-page")
    public String classroomPage(Model model) {
        List<Classroom> classrooms = classroomRepository.findByTeacher(sessionManagementService.getUsername());
        List<String> classNames = classrooms.stream()
                .map(Classroom::getName)
                .collect(Collectors.toList());
        model.addAttribute("classNames", classNames);
        return "classrooms";
    }

    @GetMapping("/view-classroom/{className}")
    public String viewClassroom(@PathVariable String className,
            Model model) {
        Classroom classroom = classroomRepository.findByNameAndTeacher(className,
                sessionManagementService.getUsername());
        model.addAttribute("classroom", classroom);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "view-classroom";
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
            return "redirect:/user/classroom/view-classroom/" + name;
        } else {
            sessionManagementService.setMessage("Tên lớp đã tồn tại");
            return "redirect:/user/classroom/create-classroom-page";
        }
    }
}
