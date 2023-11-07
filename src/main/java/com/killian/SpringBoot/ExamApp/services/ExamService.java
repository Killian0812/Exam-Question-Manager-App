package com.killian.SpringBoot.ExamApp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.killian.SpringBoot.ExamApp.models.Exam;
import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Service
public class ExamService {

    // private final Path storageFolder = Paths.get("uploads");

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    public Exam createExam(String examName, List<Long> questionIds) {

        Exam exam = new Exam();
        exam.setName(examName);

        List<Question> selectedQuestions = questionRepository.findAllById(questionIds);

        exam.setQuestions(selectedQuestions);

        return examRepository.save(exam);
    }

    public Exam getExamById(Long examId) {
        return examRepository.findById(examId).orElse(null);
    }

    public Exam processDocxFile(MultipartFile file, int grade, String subject) {

        Exam exam = new Exam();
        XWPFDocument document;
        try {
            document = new XWPFDocument(file.getInputStream());

            List<Question> questions = new ArrayList<>();
            Question currentQuestion = null;
            List<String> currentChoices = null;

            for (XWPFParagraph paragraph : document.getParagraphs()) {

                XWPFRun run = paragraph.getRuns().get(0);
                String text = run.getText(0);

                if (text == null) {
                    continue;
                }

                if (text.startsWith("Câu ")) {
                    if (currentQuestion != null) {
                        currentQuestion.setChoices(currentChoices);
                        questionRepository.save(currentQuestion);
                        questions.add(currentQuestion);
                    }
                    currentQuestion = new Question(grade, subject);
                    currentChoices = new ArrayList<>();
                    for (int i = 5; i < text.length(); i++) {
                        char c = text.charAt(i);
                        if (c == ':') {
                            currentQuestion.setText(text.substring(i + 2));
                            break;
                        }
                    }
                } else if (currentQuestion != null && currentChoices != null) {
                    if (text.startsWith("A. ")
                            || text.startsWith("B. ")
                            || text.startsWith("C. ")
                            || text.startsWith("D. ")) {

                        currentChoices.add(text.substring(3));

                        if (run.isBold())
                            currentQuestion.setAnswer(text.substring(3));
                    }
                }
            }
            // Add the last question to the current exam
            if (currentQuestion != null) {
                currentQuestion.setChoices(currentChoices);
                questionRepository.save(currentQuestion);
                questions.add(currentQuestion);
                if (exam != null) {
                    exam.setQuestions(questions);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return exam;
    }

}