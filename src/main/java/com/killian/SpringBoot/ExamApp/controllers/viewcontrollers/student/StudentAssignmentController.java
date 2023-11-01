package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.killian.SpringBoot.ExamApp.models.Assignment;
import com.killian.SpringBoot.ExamApp.models.Classroom;
import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.models.Submission;
import com.killian.SpringBoot.ExamApp.repositories.AssignmentRepository;
import com.killian.SpringBoot.ExamApp.repositories.ClassroomRepository;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.SubmissionRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/student/classroom/assignment")
public class StudentAssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @GetMapping("")
    public String assignmentList(
            @RequestParam("classCode") String classCode,
            Model model) {
        Classroom classroom = classroomRepository.findByClasscode(classCode);
        List<Assignment> assignments = assignmentRepository.findAssignmentsByClassname(classroom.getName());
        model.addAttribute("assignments", assignments);
        model.addAttribute("className", classroom.getName());
        model.addAttribute("classCode", classCode);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "student/assignments";
    }

    @GetMapping("view-assignment")
    public String viewAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("classCode") String classCode,
            Model model) {
        String className = classroomRepository.findByClasscode(classCode).getName();
        Assignment assignment = assignmentRepository.findAssignmentByClasscodeAndName(classCode, assignmentName);
        model.addAttribute("assignment", assignment);
        model.addAttribute("assignmentDeadline", assignment.getDeadline());

        Exam exam = examRepository.findByExamId(assignment.getExamId()).get(0);
        model.addAttribute("className", className);
        model.addAttribute("classCode", classCode);
        model.addAttribute("examDuration", exam.getDuration());
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();

        Submission submission = submissionRepository.findByAssignmentId(assignment.getAssignmentId(),
                sessionManagementService.getUsername());
        if (submission != null) {
            if (submission.getScore() != -1.0)
                model.addAttribute("submitted", 1);
            else
                model.addAttribute("submitted", -1);
            model.addAttribute("submissionId", submission.getSubmissionId());
        } else {
            model.addAttribute("submitted", 0);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
            // 00:49 24/11/2023
            LocalDateTime deadline = LocalDateTime.parse(assignment.getDeadline(), formatter);
            int comparison = deadline.compareTo(currentDateTime);
            if (comparison < 0)
                model.addAttribute("expired", 1);
            else
                model.addAttribute("expired", 0);
        }
        return "student/view-assignment";
    }

    @GetMapping("do-assignment")
    public String doAssignment(
            @RequestParam("assignmentName") String assignmentName,
            @RequestParam("classCode") String classCode,
            Model model) {

        Classroom classroom = classroomRepository.findByClasscode(classCode);
        String className = classroom.getName();
        Assignment assignment = assignmentRepository.findAssignmentByName(className, assignmentName).get(0);
        List<Exam> exams = examRepository.findByExamId(assignment.getExamId());
        String student = sessionManagementService.getUsername();

        Submission submission = submissionRepository.findByAssignmentId(assignment.getAssignmentId(), student);
        if (submission == null) {
            model.addAttribute("message", "Bắt đầu làm bài");
            // Get a random examCode
            Random random = new Random();
            int randomIndex = random.nextInt(exams.size());
            Exam exam = exams.get(randomIndex);
            Submission newSubmission = new Submission(student, assignment.getAssignmentId(), randomIndex,
                    exam.getQuestions().size());
            submissionRepository.save(newSubmission);
            model.addAttribute("selected", newSubmission.getSelected());
            model.addAttribute("submissionId", newSubmission.getSubmissionId());
            model.addAttribute("exam", exam);
        } else {
            Exam exam = exams.get(submission.getExamCode());
            model.addAttribute("exam", exam);
            model.addAttribute("submissionId", submission.getSubmissionId());
            model.addAttribute("selected", submission.getSelected());
        }
        model.addAttribute("className", classroom.getName());
        model.addAttribute("classCode", classCode);
        model.addAttribute("assignmentName", assignmentName);
        return "student/do-assignment";
    }

    @GetMapping("result")
    public String getResult(
            @RequestParam("submissionId") String submissionId,
            Model model) {
        Submission submission = submissionRepository.findBySubmissionId(submissionId);
        Assignment assignment = assignmentRepository.findByAssignmentId(submission.getAssignmentId());
        Exam exam = examRepository.findByExamId(assignment.getExamId()).get(submission.getExamCode());
        List<Question> questions = exam.getQuestions();
        List<Integer> choiceIndexes = submission.getSelected();
        List<String> choices = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++)
            choices.add(questions.get(i).getChoices().get(choiceIndexes.get(i)));
        model.addAttribute("choices", choices);
        model.addAttribute("questions", questions);
        model.addAttribute("submission", submission);
        model.addAttribute("assignmentName", assignment.getName());
        model.addAttribute("classCode", assignment.getClassCode());
        return "student/result";
    }
}
