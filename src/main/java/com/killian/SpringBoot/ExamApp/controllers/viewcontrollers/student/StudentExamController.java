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

import com.killian.SpringBoot.ExamApp.models.Choice;
import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.models.Submission;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.SubmissionRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/student/exam")
public class StudentExamController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SessionManagementService sessionManagementService;

    @GetMapping("/training")
    public String trainingExam(Model model) {
        sessionManagementService.clearMessage();
        return "student/exams-by-filter";
    }

    @GetMapping("/find-exam")
    public String findAllExams(
            @RequestParam("selectedSubject") String selectedSubject,
            @RequestParam("selectedGrade") int selectedGrade,
            Model model) {

        List<String> subjects = examRepository.findDistinctSubjects();
        List<Integer> grades = examRepository.findDistinctGrades();

        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);

        List<Exam> exams = examRepository.findTrainingExams(selectedSubject, selectedGrade, 0);

        model.addAttribute("selectedSubject", selectedSubject);
        model.addAttribute("selectedGrade", selectedGrade);
        model.addAttribute("exams", exams);

        return "student/exams-by-filter";
    }

    @GetMapping("/view-exam")
    public String viewAssignment(
            @RequestParam("examId") String examId,
            Model model) {

        Exam exam = examRepository.findByExamId(examId).get(0);
        model.addAttribute("exam", exam);
        model.addAttribute("examDuration", exam.getDuration());
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();

        List<Submission> submissions = submissionRepository.findByExamId(examId,
                sessionManagementService.getUsername());
        if (!submissions.isEmpty()) {
            if (submissions.size() >= 2 || submissions.get(submissions.size() - 1).getScore() != -1.0)
                model.addAttribute("submitted", 1);
            if (submissions.get(submissions.size() - 1).getScore() == -1.0)
                model.addAttribute("isDoing", 1);
        } else {
            model.addAttribute("submitted", 0);
        }
        return "student/view-exam";
    }

    @GetMapping("/do-exam")
    public String doAssignment(
            @RequestParam("examId") String examId,
            Model model) {

        List<Exam> exams = examRepository.findByExamId(examId);
        String student = sessionManagementService.getUsername();

        List<Submission> submissions = submissionRepository.findByExamId(examId, student);
        if (submissions.isEmpty() || submissions.get(submissions.size() - 1).getScore() != -1.0) {
            model.addAttribute("message", "Bắt đầu làm bài");
            // Get a random examCode
            Random random = new Random();
            int randomIndex = random.nextInt(exams.size());
            Exam exam = exams.get(randomIndex);
            Submission newSubmission = new Submission(student, randomIndex, examId,
                    exam.getQuestions().size(), exam.getDuration());
            submissionRepository.save(newSubmission);
            model.addAttribute("endTime", newSubmission.getEndTime());
            model.addAttribute("choices", newSubmission.getChoices());
            model.addAttribute("submissionId", newSubmission.getSubmissionId());
            model.addAttribute("exam", exam);
        } else {
            Submission submission = submissions.get(submissions.size() - 1);
            Exam exam = exams.get(submission.getExamCode());
            model.addAttribute("endTime", submission.getEndTime());
            model.addAttribute("exam", exam);
            model.addAttribute("submissionId", submission.getSubmissionId());
            model.addAttribute("choices", submission.getChoices());
        }
        return "student/do-exam";
    }

    @GetMapping("/submission/all-submission")
    public String getAllSubmissions(
            @RequestParam("examId") String examId,
            Model model) {
        List<Submission> submissions = submissionRepository.findByExamId(examId,
                sessionManagementService.getUsername());
        model.addAttribute("submissions", submissions);
        model.addAttribute("examId", examId);
        model.addAttribute("submission", submissions.get(0));
        return "student/submissions";
    }

    @GetMapping("/submission/view-submission")
    public String getResult(
            @RequestParam("submissionId") String submissionId,
            Model model) {
        Submission submission = submissionRepository.findBySubmissionId(submissionId);
        Exam exam = examRepository.findByExamId(submission.getExamId()).get(submission.getExamCode());
        List<Question> questions = exam.getQuestions();
        List<Choice> selectedChoices = submission.getChoices();
        List<String> choices = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            if (selectedChoices.size() < (i + 1) || selectedChoices.get(i) == null)
                choices.add("Không trả lời");
            else {
                // int index = choiceIndexes.get(i);
                // choices.add(questions.get(i).getChoices().get(index));
            }
        }
        // Thời gian bắt đầu: 17:30:38 11/02/2023
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
        DateTimeFormatter desiredformat = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime startedTime = LocalDateTime.parse(submission.getStartedTime(), format);
        LocalDateTime submittedTime = LocalDateTime.parse(submission.getSubmittedTime(), format);
        model.addAttribute("startedTime", desiredformat.format(startedTime));
        model.addAttribute("submittedTime", desiredformat.format(submittedTime));
        model.addAttribute("choices", choices);
        model.addAttribute("questions", questions);
        model.addAttribute("submission", submission);
        model.addAttribute("examId", exam.getExamId());
        return "student/view-submission";
    }
}
