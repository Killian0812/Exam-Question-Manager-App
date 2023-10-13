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
                                        "Hình vuông có 2 đường chéo", "Toán", "Chương 1: Mệnh đề và tập hợp",
                                        10, "multiple-choice"),
                        new Question("Hãy tính giá trị của biểu thức: (3x^2 - 5x + 2) khi x = 4.",
                                        Arrays.asList("2", "20", "32", "44"), "44", "Toán", "Chương 2: Đại số",
                                        10,
                                        "multiple-choice"),
                        new Question("Tìm đạo hàm của hàm số f(x) = 2x^3 - 6x^2 + 5x - 8.",
                                        Arrays.asList("f'(x) = 6x^2 - 12x + 5", "f'(x) = 6x^2 - 12x + 6",
                                                        "f'(x) = 2x^2 - 6x + 5", "f'(x) = 3x^2 - 8x + 5"),
                                        "f'(x) = 6x^2 - 12x + 5", "Toán", "Chương 3: Giải tích", 10,
                                        "multiple-choice"),
                        new Question("Cho một tam giác vuông với cạnh góc vuông có độ dài 6 cm. Tính chu vi và diện tích của tam giác.",
                                        Arrays.asList("Chu vi = 18 cm, Diện tích = 18 cm^2",
                                                        "Chu vi = 12 cm, Diện tích = 18 cm^2",
                                                        "Chu vi = 24 cm, Diện tích = 24 cm^2",
                                                        "Chu vi = 18 cm, Diện tích = 12 cm^2"),
                                        "Chu vi = 18 cm, Diện tích = 18 cm^2", "Toán", "Chương 4: Hình học", 10,
                                        "multiple-choice"),
                        new Question("Giải phương trình: 2x^2 - 7x + 3 = 0.",
                                        Arrays.asList("x = 1 và x = 3", "x = 2 và x = 1/2", "x = 3 và x = 1/3",
                                                        "x = 4 và x = 1/4"),
                                        "x = 1 và x = 3", "Toán", "Chương 5: Phương trình và bất phương trình", 10,
                                        "multiple-choice"),
                        new Question("Tìm giá trị lớn nhất và giá trị nhỏ nhất của hàm số f(x) = -x^2 + 4x - 7 trong khoảng [0, 5].",
                                        Arrays.asList("Giá trị lớn nhất: 1, Giá trị nhỏ nhất: -7",
                                                        "Giá trị lớn nhất: 7, Giá trị nhỏ nhất: -1",
                                                        "Giá trị lớn nhất: 5, Giá trị nhỏ nhất: 0",
                                                        "Giá trị lớn nhất: 4, Giá trị nhỏ nhất: -7"),
                                        "Giá trị lớn nhất: 1, Giá trị nhỏ nhất: -7", "Toán", "Chương 6: Hàm số", 10,
                                        "multiple-choice"),
                        new Question("Cho hai vectơ A(3, -2) và B(5, 7). Tính tích vô hướng của chúng.",
                                        Arrays.asList("Tích vô hướng A.B = 29", "Tích vô hướng A.B = -29",
                                                        "Tích vô hướng A.B = 27", "Tích vô hướng A.B = -27"),
                                        "Tích vô hướng A.B = 29", "Toán", "Chương 7: Đại số tuyến tính", 10,
                                        "multiple-choice"),
                        new Question("Hãy tìm nghiệm của hệ phương trình sau: 2x + 3y = 12 và x - 2y = 4.",
                                        Arrays.asList("x = 4, y = 2", "x = 2, y = 4", "x = 3, y = 3", "x = 0, y = 6"),
                                        "x = 4, y = 2", "Toán", "Chương 8: Hệ phương trình tuyến tính", 10,
                                        "multiple-choice"),
                        new Question("Tìm tích của một số tự nhiên với nó bằng 144 và tổng của nó bằng 20.",
                                        Arrays.asList("Số tự nhiên là 12", "Số tự nhiên là 16", "Số tự nhiên là 8",
                                                        "Số tự nhiên là 10"),
                                        "Số tự nhiên là 8", "Toán", "Chương 9: Số học", 10, "multiple-choice"),
                        new Question("Hãy tính giá trị của sin(π/3).",
                                        Arrays.asList("sin(π/3) = 1/2", "sin(π/3) = √3/2", "sin(π/3) = 2/√3",
                                                        "sin(π/3) = 3/2"),
                                        "sin(π/3) = √3/2", "Toán", "Chương 10: Góc và biến đổi góc", 10,
                                        "multiple-choice"),
                        new Question("Tìm nghiệm của bất phương trình: |2x - 1| < 5.",
                                        Arrays.asList("x < 3", "x > 3", "x < 2 và x > 4", "x > 2 và x < 4"),
                                        "x > 2 và x < 4", "Toán", "Chương 11: Bất phương trình", 10,
                                        "multiple-choice"));

        public void dataGenerate() {
                try {
                        questionRepository.saveAll(Math10Questions);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating data");
                }
        }

}
