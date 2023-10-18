package com.killian.SpringBoot.ExamApp.controllers.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

@Controller
@RequestMapping(path = "/user/exam")
public class ExamController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/create-exam-page")
    public String createExamPage(Model model) {
        return "create-exam";
    }

    @GetMapping("/select-subject-and-grade")
    public String createExamFromBankPage1(Model model) {

        List<String> subjects = questionRepository.findDistinctSubjects();
        List<Integer> grades = questionRepository.findDistinctGrades();
        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "select-subject-and-grade";
    }

    @GetMapping("/create-exam-from-bank-page")
    public String createExamFromBankPage(
            @RequestParam("selectedSubject") String selectedSubject,
            @RequestParam("selectedGrade") int selectedGrade,
            Model model) {

        List<String> chapters = questionRepository.findDistinctChaptersBySubjectAndGrade(selectedSubject,
                selectedGrade);

        model.addAttribute("selectedGrade", selectedGrade);
        model.addAttribute("selectedSubject", selectedSubject);
        model.addAttribute("chapters", chapters);

        return "create-exam-from-bank";
    }

    @PostMapping("/create-exam-from-bank")
    public String createQuestion(
            @RequestParam("subject") String subject,
            @RequestParam("grade") int grade,
            @RequestParam("name") String name,
            @RequestParam("amount") int amount,
            @RequestParam("questionCountForEachChapter") List<Integer> questionCountForEachChapter,
            Model model) {

        if (!examRepository.findByNameAndOwner(name, sessionManagementService.getUsername()).isEmpty()) {
            sessionManagementService.setMessage("Tên đề thi không hợp lệ!");
            return "redirect:/user/exam/select-subject-and-grade";
        }
        for (int j = 0; j < amount; j++) {

            Exam newExam = new Exam();
            newExam.setName(name);
            newExam.setSubject(subject);
            newExam.setGrade(grade);
            newExam.setExamCode(j);
            newExam.setOwner(sessionManagementService.getUsername());

            List<Question> questions = new ArrayList<>();
            List<String> chapters = questionRepository.findDistinctChaptersBySubjectAndGrade(subject, grade);
            for (int i = 0; i < chapters.size(); i++) {
                String chapter = chapters.get(i);
                List<Question> newQuestions = questionRepository.findRandomQuestionsByChapterGradeSubject(chapter,
                        subject, grade, questionCountForEachChapter.get(i));
                for (int k = 0; k < newQuestions.size(); k++) {
                    newQuestions.set(k, newQuestions.get(k).shuffleChoices());
                }
                questions.addAll(newQuestions);
            }
            Collections.shuffle(questions); // shuffle questions
            newExam.setQuestions(questions);

            examRepository.save(newExam);
        }
        sessionManagementService.setMessage("Tạo đề thi thành công!");
        return "redirect:/user/exam/view-exams-by-filter-page";
    }

    @GetMapping("/view-exams-by-filter-page")
    public String getQuestionsByFilterPage(Model model) {

        List<String> subjects = examRepository.findDistinctSubjects();
        model.addAttribute("subjects", subjects);

        // Initially, display questions from the first subject
        if (!subjects.isEmpty())
            model.addAttribute("selectedSubject", subjects.get(0));

        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "exams-by-filter";
    }

    @GetMapping("/get-exams-by-subject")
    public String getExamsBySelectedSubject(
            @RequestParam("selectedSubject") String selectedSubject,
            Model model) {

        List<String> subjects = examRepository.findDistinctSubjects();

        List<Exam> exams = examRepository.findBySubjectAndCode(selectedSubject, 0);
        List<String> examNames = exams.stream()
                .map(Exam::getName)
                .collect(Collectors.toList());

        model.addAttribute("subjects", subjects);
        model.addAttribute("selectedSubject", selectedSubject);
        model.addAttribute("examNames", examNames);

        return "exams-by-filter";
    }

    @GetMapping("/get-exam-by-name")
    public String viewExam(
            @RequestParam String name,
            @RequestParam int selectedCode,
            Model model) {

        String owner = sessionManagementService.getUsername();
        Exam exam = examRepository.findByNameAndOwner(name, owner).get(selectedCode);
        List<Integer> examCodes = examRepository.findDistinctExamCode(name, owner);
        model.addAttribute("exam", exam);
        model.addAttribute("examCodes", examCodes);
        model.addAttribute("selectedCode", selectedCode);
        return "exam-by-name";
    }
}
