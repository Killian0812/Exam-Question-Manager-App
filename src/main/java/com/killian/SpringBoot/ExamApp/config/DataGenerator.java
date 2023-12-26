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

        List<Question> Math10QuestionsC1 = Arrays.asList(
                        new Question("Trong các câu sau câu nào là mệnh đề?",
                                        Arrays.asList("Buồn ngủ quá!", "Hình vuông có 2 đường chéo", "Cay thế nhỉ!",
                                                        "Bạn ở đâu?"),
                                        Arrays.asList("Hình vuông có 2 đường chéo"), "Toán",
                                        "Chương 1: Mệnh đề và tập hợp",
                                        10, "multiple-choice"),
                        new Question("Tập hợp con của tập hợp A gồm các phần tử {1, 2, 3}. Số lượng tập hợp con của A là:",
                                        Arrays.asList("3", "6", "8", "10"), Arrays.asList("8"), "Toán",
                                        "Chương 1: Mệnh đề và tập hợp", 10, "multiple-choice"),
                        new Question("Cho U là tập hợp tất cả các số nguyên dương nhỏ hơn 10. Tập hợp A = {x | x là số chẵn trong U}. Số lượng phần tử của tập hợp A là:",
                                        Arrays.asList("4", "5", "6", "7"), Arrays.asList("4"), "Toán",
                                        "Chương 1: Mệnh đề và tập hợp", 10, "multiple-choice"));
        List<Question> Math10QuestionsC2 = Arrays.asList(
                        new Question("Giải bất phương 3x - 5 > 2x + 7. Kết quả của x là",
                                        Arrays.asList("x > 12", "x > -12", "x < -12", "x < 12"),
                                        Arrays.asList("x > 12"), "Toán",
                                        "Chương 2: Bất phương trình và hệ bất phương trình bậc nhất 2 ẩn",
                                        10, "multiple-choice"),
                        new Question("Hệ bất phương trình { 2x - 3y ≤ 6, x + 4y > 8 } có bao nhiêu nghiệm nguyên?",
                                        Arrays.asList("0", "1", "Vô số", "2"), Arrays.asList("Vô số"), "Toán",
                                        "Chương 2: Bất phương trình và hệ bất phương trình bậc nhất 2 ẩn", 10,
                                        "multiple-choice"),
                        new Question("Giải bất phương trình 4x^2 - 12x + 9 < 0. Tập nghiệm của bất phương trình là:",
                                        Arrays.asList("x ∈ (−∞,1)", "x ∈ (1,3)", "x ∈ (3,∞)", "x ∈ (1,3)"),
                                        Arrays.asList("x ∈ (1,3)"), "Toán",
                                        "Chương 2: Bất phương trình và hệ bất phương trình bậc nhất 2 ẩn", 10,
                                        "multiple-choice"));
        List<Question> Math10QuestionsC3 = Arrays.asList(
                        new Question("Trong tam giác ABC, đỉnh A nằm trên đường tròn ngoại tiếp tam giác, độ dài hai cạnh AB và AC lần lượt là 5 và 12. Độ dài cạnh BC là bao nhiêu?",
                                        Arrays.asList("7", "13", "17", "25"), Arrays.asList("13"), "Toán",
                                        "Chương 3: Hệ thức lượng trong tam giác",
                                        10, "multiple-choice"),
                        new Question("Trong tam giác ABC vuông tại A, đường cao AH chia cạnh BC thành hai đoạn có độ dài 4 và 6. Độ dài cạnh AB là bao nhiêu?",
                                        Arrays.asList("2", "5", "8", "10"), Arrays.asList("8"), "Toán",
                                        "Chương 3: Hệ thức lượng trong tam giác", 10,
                                        "multiple-choice"),
                        new Question("Tính diện tích tam giác vuông cân có độ dài cạnh huyền là 8 cm:",
                                        Arrays.asList("64", "16", "24", "80"), Arrays.asList("16"), "Toán",
                                        "Chương 3: Hệ thức lượng trong tam giác", 10,
                                        "multiple-choice"));
        List<Question> Math10QuestionsC4 = Arrays.asList(
                        new Question("Độ lớn của vectơ a = ⟨3,4⟩ là bao nhiêu?",
                                        Arrays.asList("7", "5", "25", "12"), Arrays.asList("5"), "Toán",
                                        "Chương 4: Vectơ", 10, "multiple-choice"),
                        new Question("Cho hai vectơ u = ⟨2,-3⟩ và v = ⟨4,1⟩. Tổng của hai vectơ này là:",
                                        Arrays.asList("⟨6,-2⟩", "⟨6,-4⟩", "⟨2,4⟩", "⟨8,-2⟩"), Arrays.asList("⟨6,-2⟩"),
                                        "Toán", "Chương 4: Vectơ", 10, "multiple-choice"),
                        new Question("Nếu a = ⟨5,-2⟩ và b = ⟨-3,6⟩, tích vô hướng của hai vectơ này là bao nhiêu?",
                                        Arrays.asList("27", "-27", "180", "-180"), Arrays.asList("-27"), "Toán",
                                        "Chương 4: Vectơ", 10, "multiple-choice"));

        public void dataGenerate() {
                try {
                        questionRepository.saveAll(Math10QuestionsC1);
                        questionRepository.saveAll(Math10QuestionsC2);
                        questionRepository.saveAll(Math10QuestionsC3);
                        questionRepository.saveAll(Math10QuestionsC4);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating data");
                }
        }

}
