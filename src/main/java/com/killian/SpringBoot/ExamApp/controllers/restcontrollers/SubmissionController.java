package com.killian.SpringBoot.ExamApp.controllers.restcontrollers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.killian.SpringBoot.ExamApp.models.Assignment;
import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.models.Submission;
import com.killian.SpringBoot.ExamApp.repositories.AssignmentRepository;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.SubmissionRepository;

@RestController
@RequestMapping(path = "/api/submission")
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ExamRepository examRepository;

    @PostMapping("/submit-choice")
    @ResponseStatus(HttpStatus.OK)
    public String submitChoice(
            @RequestParam("submissionId") String submissionId,
            @RequestParam("questionIndex") int questionIndex,
            @RequestParam("newChoice") int newChoice) {
        Submission submission = submissionRepository.findBySubmissionId(submissionId);
        submission.setSelected(questionIndex, newChoice);
        submissionRepository.save(submission);
        return "OK";
    }

    @PostMapping("submit-assignment")
    @ResponseStatus(HttpStatus.OK)
    public String submitAssignment(
            @RequestParam("choicesJsonString") String choicesJsonString,
            @RequestParam("submissionId") String submissionId) {

        Submission submission = submissionRepository.findBySubmissionId(submissionId);
        Assignment assignment = assignmentRepository.findByAssignmentId(submission.getAssignmentId());
        Exam exam = examRepository.findByExamIdAndCode(assignment.getExamId(), submission.getExamCode());
        List<String> answers = new ArrayList<>();
        List<Question> questions = exam.getQuestions();
        for (Question question : questions) {
            answers.add(question.getAnswer());
        }
        int countCorrectAnswer = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> selectedChoices = mapper.readValue(choicesJsonString, new TypeReference<List<String>>() {
            });
            for (int i = 0; i < answers.size(); i++) {
                if (selectedChoices.get(i).equals(answers.get(i)))
                    countCorrectAnswer++;
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        double newScore = (double) countCorrectAnswer / questions.size() * 10.0;
        double roundedScore = Math.round(newScore * 10) / 10.0;
        submission.setScore(roundedScore);
        submission.setSubmittedTime(getCurrentDateTime());
        submissionRepository.save(submission);
        return "OK";
    }

    private static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
