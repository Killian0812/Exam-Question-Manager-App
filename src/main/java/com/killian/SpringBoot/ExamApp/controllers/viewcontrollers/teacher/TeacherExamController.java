package com.killian.SpringBoot.ExamApp.controllers.viewcontrollers.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;
import com.killian.SpringBoot.ExamApp.services.ExamService;
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
    private ExamService examService;

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

    @GetMapping("/create-own-exam-page")
    public String createExamManuallyPage(Model model) {
        model.addAttribute("message", sessionManagementService.getMessage());
        sessionManagementService.clearMessage();
        return "teacher/create-exam-by-docx";
    }

    @PostMapping("/upload-docx")
    public String createExamFromDocx(@RequestParam("subject") String subject,
            @RequestParam("grade") int grade,
            @RequestParam("name") String name,
            @RequestParam("duration") int duration,
            @RequestParam("file") MultipartFile file,
            Model model) {

        List<Exam> exams = examRepository.findByNameAndOwner(name, sessionManagementService.getUsername());
        if (!exams.isEmpty()) {
            sessionManagementService.setMessage("Tên đề thi trùng lặp");
            return "redirect:/teacher/exam/create-own-exam-page";
        }

        // process docx file
        Exam exam = examService.processDocxFile(file, grade, subject);
        exam.setName(name);
        exam.setGrade(grade);
        exam.setSubject(subject);
        exam.setDuration(duration);
        exam.setExamCode(0);
        exam.setExamId();
        exam.setOwner(sessionManagementService.getUsername());
        examRepository.save(exam);

        sessionManagementService.setMessage("Tạo đề thi thành công!");
        return "redirect:/teacher/exam/get-exam-by-examId?examId=" + exam.getExamId() + "&selectedCode=0";
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
    public void exportExamsToZip(
            HttpServletResponse response,
            @RequestParam("examId") String examId) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=exams.zip");

        try {
            // Create a temporary directory to store individual PDFs
            File tempDir = Files.createTempDirectory("exams").toFile();

            // Create a ZIP file to store the individual PDFs
            File zipFile = new File(tempDir, "exams.zip");
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

            List<Integer> examCodes = examRepository.findDistinctExamCode(examId);

            for (int k = 0; k < examCodes.size(); k++) {

                int examCode = examCodes.get(k);
                // Generate individual PDFs for each exam
                Exam exam = examRepository.findByExamIdAndCode(examId, examCode);

                PDDocument document = new PDDocument();
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                // Create a content stream for the page
                PDPageContentStream contentStream = new PDPageContentStream(document, page);

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
                
                // Create a variable to keep track of the current y-coordinate
                float currentY = 650; // Initial position for the first line

                List<Question> questions = exam.getQuestions();
                for (int i = 0; i < questions.size(); i++) {
                    Question question = questions.get(i);
                    String fullQuestionText = "Câu " + (i + 1) + ": " + question.getText();

                    List<String> lines = splitTextManually(fullQuestionText, font, 12, 400); // Adjust the width as
                                                                                             // needed
                    for (String line : lines) {
                        // If the current Y-coordinate goes beyond the page boundary, create a new page
                        if (currentY < 50) {
                            contentStream.endText();
                            contentStream.close();
                            page = new PDPage(PDRectangle.A4);
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            contentStream.setFont(font, 12);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(50, 800);
                            currentY = 800; // Reset the Y-coordinate for the new page
                        }
                        contentStream.showText(line);
                        contentStream.newLineAtOffset(0, -20); // next line
                        currentY -= 20;
                    }

                    contentStream.newLineAtOffset(10, 0);
                    for (int j = 0; j < question.getChoices().size(); j++) {
                        if (currentY < 50) {
                            contentStream.endText();
                            contentStream.close();
                            page = new PDPage(PDRectangle.A4);
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            contentStream.setFont(font, 12);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(50, 800);
                            currentY = 800; // Reset the Y-coordinate for the new page
                        }
                        // Add each choice
                        contentStream.showText(labelGenerator.getLabel(j) + " " + question.getChoices().get(j));
                        contentStream.newLineAtOffset(0, -20);
                        currentY -= 20;
                    }
                    contentStream.newLineAtOffset(-10, -20);
                    currentY -= 20;
                }

                contentStream.endText();
                contentStream.close();

                // Save the individual PDF to a temporary file
                File pdfFile = new File(tempDir, exam.getName() + "_" + exam.getExamCode() + ".pdf");
                document.save(pdfFile);

                // Close the individual PDF document
                document.close();

                // Add the individual PDF to the ZIP file
                ZipEntry zipEntry = new ZipEntry(exam.getName() + "_" + exam.getExamCode() + ".pdf");
                zipOutputStream.putNextEntry(zipEntry);

                // Create input stream for each pdf
                FileInputStream fileInputStream = new FileInputStream(pdfFile);

                // Copy into zip file
                IOUtils.copy(fileInputStream, zipOutputStream);

                // Code input stream and current zip output stream
                fileInputStream.close();
                zipOutputStream.closeEntry();

            }

            // Close the ZIP output stream
            zipOutputStream.close();

            // Send the ZIP file as the response
            try (OutputStream outputStream = response.getOutputStream()) {
                FileUtils.copyFile(zipFile, outputStream);
                outputStream.flush();
            }

            // Delete the temporary directory and files
            FileUtils.deleteDirectory(tempDir);
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
