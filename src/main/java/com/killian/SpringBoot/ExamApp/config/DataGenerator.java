package com.killian.SpringBoot.ExamApp.config;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.killian.SpringBoot.ExamApp.models.Question;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

@Configuration
public class DataGenerator {

        @Autowired
        QuestionRepository questionRepository;

        List<Question> Math10Questions = Arrays.asList(
                        new Question("Trong các câu sau câu nào là mệnh đề?",
                                        Arrays.asList("Buồn ngủ quá!", "Hình vuông có 2 đường chéo", "Cay thế nhỉ!",
                                                        "Bạn ở đâu?"),
                                        Arrays.asList("Hình vuông có 2 đường chéo"), "Toán",
                                        "Chương 1: Mệnh đề và tập hợp",
                                        10, "multiple-choice"),
                        new Question("Hãy tính giá trị của biểu thức: (3x^2 - 5x + 2) khi x = 4.",
                                        Arrays.asList("2", "20", "32", "44"), Arrays.asList("44"), "Toán",
                                        "Chương 2: Đại số",
                                        10,
                                        "multiple-choice"),
                        new Question("Tìm đạo hàm của hàm số f(x) = 2x^3 - 6x^2 + 5x - 8.",
                                        Arrays.asList("f'(x) = 6x^2 - 12x + 5", "f'(x) = 6x^2 - 12x + 6",
                                                        "f'(x) = 2x^2 - 6x + 5", "f'(x) = 3x^2 - 8x + 5"),
                                        Arrays.asList("f'(x) = 6x^2 - 12x + 5"), "Toán", "Chương 3: Giải tích", 10,
                                        "multiple-choice"));

        public void dataGenerate() {
                try {
                        questionRepository.saveAll(Math10Questions);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating data");
                }
        }

}
