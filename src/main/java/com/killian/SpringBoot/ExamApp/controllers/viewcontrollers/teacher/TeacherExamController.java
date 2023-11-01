package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import com.killian.SpringBoot.ExamApp.services.LabelGenerator;
import com.killian.SpringBoot.ExamApp.services.SessionManagementService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/teacher/exam")
public class TeacherExamController {

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LabelGenerator labelGenerator;

    @GetMapping("/create-exam-page")
    public String createExamPage(Model model) {
        return "teacher/create-exam";
    }

    @GetMapping("/select-subject-and-grade")
    public String selectSubjectAndGrade(Model model) {

        List<String> subjects = questionRepository.findDistinctSubjects();
        List<Integer> grades = questionRepository.findDistinctGrades();
        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/select-subject-and-grade";
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

        List<Integer> limit = new ArrayList<>();
        for (String chapter : chapters) {
            limit.add(questionRepository.findNumberOfDistinctQuestionsByChapterAndGrade(chapter, selectedGrade));
        }
        model.addAttribute("limit", limit);

        return "teacher/create-exam-from-bank";
    }

    @PostMapping("/create-exam-from-bank")
    public String createQuestion(
            @RequestParam("subject") String subject,
            @RequestParam("grade") int grade,
            @RequestParam("name") String name,
            @RequestParam("amount") int amount,
            @RequestParam("duration") int duration,
            @RequestParam("questionCountForEachChapter") List<Integer> questionCountForEachChapter,
            Model model) {

        if (!examRepository.findByNameAndOwner(name, sessionManagementService.getUsername()).isEmpty()) {
            sessionManagementService.setMessage("Tên đề thi không hợp lệ!");
            return "redirect:/teacher/exam/select-subject-and-grade";
        }

        String tmp = null;
        for (int j = 0; j < amount; j++) {

            Exam newExam = new Exam();
            newExam.setName(name);
            newExam.setSubject(subject);
            newExam.setGrade(grade);
            newExam.setExamCode(j);
            newExam.setDuration(duration);
            newExam.setOwner(sessionManagementService.getUsername());
            if (tmp != null)
                newExam.setExamId(tmp);
            else {
                newExam.setExamId();
                tmp = newExam.getExamId();
            }
            List<Question> questions = new ArrayList<>();
            List<String> chapters = questionRepository.findDistinctChaptersBySubjectAndGrade(subject, grade);
            for (int i = 0; i < chapters.size(); i++) {
                int count = questionCountForEachChapter.get(i);
                if (count == 0)
                    continue;
                String chapter = chapters.get(i);
                List<String> newQuestionTexts = questionRepository.findDistinctRandomQuestionTextsByChapterGradeSubject(
                        chapter,
                        subject, grade, count);
                for (String text : newQuestionTexts) {
                    Question newQuestion = questionRepository.findFirstQuestionByText(text);
                    questions.add(newQuestion);
                }
            }
            if (j > 0) {
                Collections.shuffle(questions); // shuffle questions
                for (int i = 0; i < questions.size(); i++) {
                    Question oldQuestion = questions.get(i);
                    Question newQuestion = new Question(oldQuestion.getText(), oldQuestion.getChoices(),
                            oldQuestion.getAnswer(), oldQuestion.getSubject(), oldQuestion.getChapter(),
                            oldQuestion.getGrade(), oldQuestion.getQuestionType());
                    newQuestion.shuffleChoices();
                    questions.set(i, questionRepository.save(newQuestion));
                }
            }
            newExam.setQuestions(questions);
            examRepository.save(newExam);
        }
        sessionManagementService.setMessage("Tạo đề thi thành công!");
        return "redirect:/teacher/exam/get-exam-by-examId?examId=" + tmp + "&selectedCode=0";
    }

    @GetMapping("/view-exams-by-filter-page")
    public String getQuestionsByFilterPage(Model model) {

        List<String> subjects = examRepository.findDistinctSubjects();
        List<Integer> grades = examRepository.findDistinctGrades();

        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);

        // Initially, display questions from the first subject
        if (!subjects.isEmpty())
            model.addAttribute("selectedSubject", subjects.get(0));
        if (!grades.isEmpty())
            model.addAttribute("selectedGrade", grades.get(0));

        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/exams-by-filter";
    }

    @GetMapping("/get-exams-by-subject-and-grade")
    public String getExamsBySelectedSubject(
            @RequestParam("selectedSubject") String selectedSubject,
            @RequestParam("selectedGrade") int selectedGrade,
            Model model) {

        List<String> subjects = examRepository.findDistinctSubjects();
        List<Integer> grades = examRepository.findDistinctGrades();

        model.addAttribute("subjects", subjects);
        model.addAttribute("grades", grades);

        List<Exam> exams = examRepository.findBySubjectGradeAndCode(selectedSubject, selectedGrade, 0);

        model.addAttribute("selectedSubject", selectedSubject);
        model.addAttribute("selectedGrade", selectedGrade);
        model.addAttribute("exams", exams);

        return "teacher/exams-by-filter";
    }

    @GetMapping("/get-exam-by-examId")
    public String viewExam(
            @RequestParam("examId") String examId,
            @RequestParam("selectedCode") int selectedCode,
            Model model) {

        List<Exam> exams = examRepository.findByExamId(examId);
        Exam exam = exams.get(selectedCode);
        List<Integer> examCodes = examRepository.findDistinctExamCode(examId);
        model.addAttribute("exam", exam);
        model.addAttribute("examCodes", examCodes);
        model.addAttribute("selectedCode", selectedCode);
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/exam-by-examId";
    }

    @GetMapping("/export-pdf")
    public void exportExamPDF(
            HttpServletResponse response,
            @RequestParam("examId") String examId,
            @RequestParam("examCode") int examCode) {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=exam.pdf");

        try {
            // Create a new document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            Exam exam = examRepository.findByExamIdAndCode(examId, examCode);

            ClassPathResource fontResource = new ClassPathResource("/static/fonts/arial-unicode-ms.ttf");
            ClassPathResource BoldFontResource = new ClassPathResource("/static/fonts/arial-unicode-ms-bold.ttf");
            PDType0Font font = PDType0Font.load(document, fontResource.getFile());
            PDType0Font boldFont = PDType0Font.load(document, BoldFontResource.getFile());

            // Exam details
            contentStream.setFont(boldFont, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Bài thi: " + exam.getName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Khối lớp: " + exam.getGrade());
            contentStream.showText("      Môn học: " + exam.getSubject());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Thời gian làm bài: " + exam.getDuration() + " phút");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Đề số: 00" + (exam.getExamCode() + 1));
            contentStream.endText();

            // Questions
            contentStream.setFont(font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 650); // Set the initial position for the first line

            List<Question> questions = exam.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                String fullQuestionText = "Câu " + (i + 1) + ": " + question.getText();

                List<String> lines = splitTextManually(fullQuestionText, font, 12, 400); // Adjust the width as needed
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -20); // next line
                }

                contentStream.newLineAtOffset(10, 0);
                for (int j = 0; j < question.getChoices().size(); j++) {
                    // Add each choice
                    contentStream.showText(labelGenerator.getLabel(j) + " " + question.getChoices().get(j));
                    contentStream.newLineAtOffset(0, -20);
                }
                contentStream.newLineAtOffset(-10, -20);
            }

            contentStream.endText();
            contentStream.close();

            // Save the PDF to the response output stream
            document.save(response.getOutputStream());
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> splitTextManually(String text, PDType0Font font, float fontSize, float maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        float lineWidth = 0;

        for (String word : words) {
            try {
                float wordWidth = font.getStringWidth(word) / 1000 * fontSize;
                if (lineWidth + wordWidth > maxWidth) {
                    lines.add(line.toString());
                    line = new StringBuilder();
                    lineWidth = 0;
                }
                line.append(word).append(" ");
                lineWidth += wordWidth;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lines.add(line.toString());
        return lines;
    }

}
