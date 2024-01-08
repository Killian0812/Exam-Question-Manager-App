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

        public void generateMath10() {
                List<Question> Math10QuestionsC1 = Arrays.asList(
                                new Question("Trong các câu sau câu nào là mệnh đề?",
                                                Arrays.asList("Buồn ngủ quá!", "Hình vuông có 2 đường chéo",
                                                                "Cay thế nhỉ!",
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
                                                Arrays.asList("x ∈ (−∞,1)", "x ∈ (1,-3)", "x ∈ (3,∞)", "x ∈ (1,3)"),
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
                                                Arrays.asList("⟨6,-2⟩", "⟨6,-4⟩", "⟨2,4⟩", "⟨8,-2⟩"),
                                                Arrays.asList("⟨6,-2⟩"),
                                                "Toán", "Chương 4: Vectơ", 10, "multiple-choice"),
                                new Question("Nếu a = ⟨5,-2⟩ và b = ⟨-3,6⟩, tích vô hướng của hai vectơ này là bao nhiêu?",
                                                Arrays.asList("27", "-27", "180", "-180"), Arrays.asList("-27"), "Toán",
                                                "Chương 4: Vectơ", 10, "multiple-choice"));

                List<Question> Math10QuestionsC5 = Arrays.asList(
                                new Question("Cho dãy số: 2, 5, 8, 11, x. Nếu x là số cuối cùng trong dãy, giá trị của x là:",
                                                Arrays.asList("13", "14", "15", "16"), Arrays.asList("14"), "Toán",
                                                "Chương 5: Các số đặc trưng của mẫu số liệu không ghép nhóm", 10,
                                                "multiple-choice"),
                                new Question("Tìm số trung vị của dãy số: 10, 15, 20, 25, 30.",
                                                Arrays.asList("20", "22", "25", "27"), Arrays.asList("20"), "Toán",
                                                "Chương 5: Các số đặc trưng của mẫu số liệu không ghép nhóm", 10,
                                                "multiple-choice"),
                                new Question("Nếu trung bình cộng của 5 số là 30 và 4 trong số đó đã biết là 25, 40, 35, 20, số thứ 5 là:",
                                                Arrays.asList("20", "25", "30", "35"), Arrays.asList("35"), "Toán",
                                                "Chương 5: Các số đặc trưng của mẫu số liệu không ghép nhóm", 10,
                                                "multiple-choice"));

                List<Question> Math10QuestionsC6 = Arrays.asList(
                                new Question("Đồ thị hàm số y = x^2 - 4x + 3 là một:",
                                                Arrays.asList("Đường thẳng", "Parabol", "Hiperbol", "Elip"),
                                                Arrays.asList("Parabol"), "Toán",
                                                "Chương 6: Hàm số, đồ thị và ứng dụng", 10, "multiple-choice"),
                                new Question("Tìm giá trị lớn nhất của hàm số y = -x^2 + 4x + 5.",
                                                Arrays.asList("7", "9", "11", "13"), Arrays.asList("9"), "Toán",
                                                "Chương 6: Hàm số, đồ thị và ứng dụng", 10, "multiple-choice"),
                                new Question("Hàm số y = 2x - 1 có đồ thị là:",
                                                Arrays.asList("Đường thẳng", "Parabol", "Hiperbol", "Elip"),
                                                Arrays.asList("Đường thẳng"), "Toán",
                                                "Chương 6: Hàm số, đồ thị và ứng dụng", 10, "multiple-choice"));

                List<Question> Math10QuestionsC7 = Arrays.asList(
                                new Question("Tính khoảng cách giữa hai điểm A(3, 4) và B(7, 1).",
                                                Arrays.asList("3", "4", "5", "6"), Arrays.asList("5"), "Toán",
                                                "Chương 7: Phương pháp tọa độ trong mặt phẳng", 10, "multiple-choice"),
                                new Question("Điểm nằm giữa hai điểm A(3, 4) và B(9, 6) có tọa độ là:",
                                                Arrays.asList("(6, 5)", "(4, 3)", "(8, 5)", "(5, 6)"),
                                                Arrays.asList("(6, 5)"), "Toán",
                                                "Chương 7: Phương pháp tọa độ trong mặt phẳng", 10, "multiple-choice"),
                                new Question("Điểm C(2, 6) là điểm nằm trên trục hoành hay trục tung?",
                                                Arrays.asList("Trên trục hoành", "Trên trục tung",
                                                                "Không nằm trên cả hai", "Nằm trên cả hai"),
                                                Arrays.asList("Trên trục hoành"), "Toán",
                                                "Chương 7: Phương pháp tọa độ trong mặt phẳng", 10, "multiple-choice"));

                List<Question> Math10QuestionsC8 = Arrays.asList(
                                new Question("Số cách chọn 3 quả bóng từ 8 quả là:",
                                                Arrays.asList("56", "84", "120", "56"), Arrays.asList("56"), "Toán",
                                                "Chương 8: Đại số tổ hợp", 10, "multiple-choice"),
                                new Question("Có bao nhiêu cách để sắp xếp 5 quyển sách trên một kệ?",
                                                Arrays.asList("20", "60", "120", "150"), Arrays.asList("120"), "Toán",
                                                "Chương 8: Đại số tổ hợp", 10, "multiple-choice"),
                                new Question("Số cách xếp 6 sách vào một tủ sách là:",
                                                Arrays.asList("36", "720", "120", "360"), Arrays.asList("720"), "Toán",
                                                "Chương 8: Đại số tổ hợp", 10, "multiple-choice"));

                List<Question> Math10QuestionsC9 = Arrays.asList(
                                new Question("Ném một xúc xắc sáu mặt. Xác suất để số trên mặt xúc xắc là số chẵn là bao nhiêu?",
                                                Arrays.asList("1/2", "1/3", "1/4", "2/3"), Arrays.asList("1/2"), "Toán",
                                                "Chương 9: Tính xác suất theo định nghĩa cổ điển", 10,
                                                "multiple-choice"),
                                new Question("Lấy một lá bài từ một bộ bài 52 lá. Xác suất để lá bài đó là lá bích là:",
                                                Arrays.asList("1/4", "1/13", "1/26", "1/52"), Arrays.asList("1/13"),
                                                "Toán", "Chương 9: Tính xác suất theo định nghĩa cổ điển", 10,
                                                "multiple-choice"));

                try {
                        questionRepository.saveAll(Math10QuestionsC1);
                        questionRepository.saveAll(Math10QuestionsC2);
                        questionRepository.saveAll(Math10QuestionsC3);
                        questionRepository.saveAll(Math10QuestionsC4);
                        questionRepository.saveAll(Math10QuestionsC5);
                        questionRepository.saveAll(Math10QuestionsC6);
                        questionRepository.saveAll(Math10QuestionsC7);
                        questionRepository.saveAll(Math10QuestionsC8);
                        questionRepository.saveAll(Math10QuestionsC9);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating Math 10 data");
                }
        }

        public void generateBiology10() {
                List<Question> Biology10QuestionsC1 = Arrays.asList(
                                new Question("Thành phần nào sau đây không phải là một phần của tế bào?",
                                                Arrays.asList("ADN", "RNA", "Protein", "Glucose"),
                                                Arrays.asList("Glucose"), "Sinh học",
                                                "Chương 1: Thành phần hóa học của tế bào",
                                                10, "multiple-choice"),
                                new Question("Nguyên tử thành phần chủ yếu trong các phân tử hữu cơ của tế bào là:",
                                                Arrays.asList("Hydro", "Oxy", "Carbon", "Nitơ"),
                                                Arrays.asList("Carbon"), "Sinh học",
                                                "Chương 1: Thành phần hóa học của tế bào",
                                                10, "multiple-choice"));

                List<Question> Biology10QuestionsC2 = Arrays.asList(
                                new Question("Cấu trúc nào sau đây không phải là một phần của tế bào?",
                                                Arrays.asList("Lysosome", "Ribosome", "Mitochondria", "Enzyme"),
                                                Arrays.asList("Enzyme"), "Sinh học", "Chương 2: Cấu trúc tế bào", 10,
                                                "multiple-choice"),
                                new Question("Chức năng chính của tế bào gốc là:",
                                                Arrays.asList("Tự tái tạo", "Tạo ra protein", "Tạo ra ATP", "Phân bào"),
                                                Arrays.asList("Tự tái tạo"), "Sinh học", "Chương 2: Cấu trúc tế bào",
                                                10,
                                                "multiple-choice"));

                List<Question> Biology10QuestionsC3 = Arrays.asList(
                                new Question("Quá trình trao đổi chất diễn ra chủ yếu thông qua cơ chế nào?",
                                                Arrays.asList("Sự hấp thụ", "Nhu động", "Sự hoạt động enzyme",
                                                                "Difusion"),
                                                Arrays.asList("Difusion"), "Sinh học",
                                                "Chương 3: Trao đổi chất qua màng và truyền tin tế bào",
                                                10, "multiple-choice"),
                                new Question("Màng tế bào có cấu trúc như thế nào?",
                                                Arrays.asList("1 lớp lipid", "2 lớp protein",
                                                                "2 lớp lipid và 1 lớp protein",
                                                                "1 lớp protein và 2 lớp lipid"),
                                                Arrays.asList("2 lớp lipid và 1 lớp protein"), "Sinh học",
                                                "Chương 3: Trao đổi chất qua màng và truyền tin tế bào",
                                                10, "multiple-choice"));

                List<Question> Biology10QuestionsC4 = Arrays.asList(
                                new Question("Quá trình nào sau đây không phải là một phần của chuyển hóa năng lượng trong tế bào?",
                                                Arrays.asList("Glycolysis", "Photosynthesis", "Respiration",
                                                                "Digestion"),
                                                Arrays.asList("Digestion"), "Sinh học",
                                                "Chương 4: Chuyển hóa năng lượng trong tế bào",
                                                10, "multiple-choice"),
                                new Question("Năng lượng trong tế bào được lưu trữ chủ yếu dưới dạng:",
                                                Arrays.asList("Glucose", "ATP", "Protein", "Water"),
                                                Arrays.asList("ATP"), "Sinh học",
                                                "Chương 4: Chuyển hóa năng lượng trong tế bào",
                                                10, "multiple-choice"));

                List<Question> Biology10QuestionsC5 = Arrays.asList(
                                new Question("Quá trình nào sau đây là phần quan trọng trong chu kỳ tế bào?",
                                                Arrays.asList("Kỳ tự phục hồi", "G1", "S", "G2"),
                                                Arrays.asList("S"), "Sinh học", "Chương 5: Chu kỳ tế bào và phân bào",
                                                10, "multiple-choice"),
                                new Question("Điều gì xảy ra trong giai đoạn G1 của chu kỳ tế bào?",
                                                Arrays.asList("Phân bào", "Tăng kích thước và chuẩn bị tổ chức lại",
                                                                "Chuẩn bị cho sự chia tách", "Chu kỳ tự phục hồi"),
                                                Arrays.asList("Tăng kích thước và chuẩn bị tổ chức lại"), "Sinh học",
                                                "Chương 5: Chu kỳ tế bào và phân bào",
                                                10, "multiple-choice"));

                List<Question> Biology10QuestionsC6 = Arrays.asList(
                                new Question("Vi sinh vật nào sau đây không thuộc về các nhóm vi sinh vật chính?",
                                                Arrays.asList("Bacteria", "Virus", "Fungi", "Protozoa"),
                                                Arrays.asList("Virus"), "Sinh học", "Chương 6: Sinh học vi sinh vật",
                                                10, "multiple-choice"),
                                new Question("Sự phân chia tế bào của vi sinh vật được gọi là:",
                                                Arrays.asList("Replication", "Mitosis", "Binary fission", "Meiosis"),
                                                Arrays.asList("Binary fission"), "Sinh học",
                                                "Chương 6: Sinh học vi sinh vật",
                                                10, "multiple-choice"));

                List<Question> Biology10QuestionsC7 = Arrays.asList(
                                new Question("Virus có thể sinh sản như thế nào?",
                                                Arrays.asList("Bằng cách tiến hóa", "Bằng phương pháp tự phục hồi",
                                                                "Bằng cách sử dụng nguồn năng lượng từ môi trường",
                                                                "Bằng cách xâm nhập và lây nhiễm tế bào khác"),
                                                Arrays.asList("Bằng cách xâm nhập và lây nhiễm tế bào khác"),
                                                "Sinh học",
                                                "Chương 7: Virus",
                                                10, "multiple-choice"),
                                new Question("Cấu trúc nào dưới đây không phải là một phần của virus?",
                                                Arrays.asList("Capsid", "Lipid bilayer", "Genome", "Envelope"),
                                                Arrays.asList("Lipid bilayer"), "Sinh học", "Chương 7: Virus",
                                                10, "multiple-choice"));

                try {
                        questionRepository.saveAll(Biology10QuestionsC1);
                        questionRepository.saveAll(Biology10QuestionsC2);
                        questionRepository.saveAll(Biology10QuestionsC3);
                        questionRepository.saveAll(Biology10QuestionsC4);
                        questionRepository.saveAll(Biology10QuestionsC5);
                        questionRepository.saveAll(Biology10QuestionsC6);
                        questionRepository.saveAll(Biology10QuestionsC7);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating Bio 10 data");
                }
        }

        public void generateChemistry10() {
                List<Question> Chemistry10QuestionsC1 = Arrays.asList(
                                new Question("Nguyên tử được tạo thành từ các phần tử nào?",
                                                Arrays.asList("Proton", "Neutron", "Electron", "All of the above"),
                                                Arrays.asList("All of the above"), "Hóa học",
                                                "Chương 1: Cấu tạo nguyên tử",
                                                10, "multiple-choice"),
                                new Question("Trong nguyên tử, electron có tác dụng gì?",
                                                Arrays.asList("Chứa hạt nhân", "Xác định phân tử",
                                                                "Xác định tính hóa học", "Cấu tạo năng lượng"),
                                                Arrays.asList("Xác định tính hóa học"), "Hóa học",
                                                "Chương 1: Cấu tạo nguyên tử",
                                                10, "multiple-choice"));

                List<Question> Chemistry10QuestionsC2 = Arrays.asList(
                                new Question("Nguyên tố nào sau đây có số nguyên tử lớn nhất trong bảng tuần hoàn?",
                                                Arrays.asList("Hydrogen", "Oxygen", "Uranium", "Carbon"),
                                                Arrays.asList("Uranium"), "Hóa học",
                                                "Chương 2: Bảng tuần hoàn các nguyên tố hóa học và định luật tuần hoàn",
                                                10, "multiple-choice"),
                                new Question("Nguyên tố nào là khí quý hiếm trong bảng tuần hoàn?",
                                                Arrays.asList("Helium", "Neon", "Argon", "Krypton"),
                                                Arrays.asList("Argon"), "Hóa học",
                                                "Chương 2: Bảng tuần hoàn các nguyên tố hóa học và định luật tuần hoàn",
                                                10, "multiple-choice"));

                List<Question> Chemistry10QuestionsC3 = Arrays.asList(
                                new Question("Liên kết hóa học nào xảy ra khi electron chia sẻ giữa các nguyên tử?",
                                                Arrays.asList("Liên kết ion", "Liên kết cộng hóa trị",
                                                                "Liên kết kim loại", "Liên kết cộng hóa học"),
                                                Arrays.asList("Liên kết cộng hóa trị"), "Hóa học",
                                                "Chương 3: Liên kết hóa học",
                                                10, "multiple-choice"),
                                new Question("Mạch C4H10 có thể liên kết bằng liên kết nào?",
                                                Arrays.asList("Liên kết đơn", "Liên kết đôi", "Liên kết ba",
                                                                "Không liên kết"),
                                                Arrays.asList("Liên kết đôi"), "Hóa học", "Chương 3: Liên kết hóa học",
                                                10, "multiple-choice"));

                List<Question> Chemistry10QuestionsC4 = Arrays.asList(
                                new Question("Phản ứng nào sau đây không phải là phản ứng Oxi hóa - Khử?",
                                                Arrays.asList("Nước điện phân", "Quang hợp", "Sự cháy",
                                                                "Sự hoạt động của pin"),
                                                Arrays.asList("Quang hợp"), "Hóa học",
                                                "Chương 4: Phản ứng Oxi hóa - Khử",
                                                10, "multiple-choice"),
                                new Question("Ở đâu trong cơ thể con người diễn ra quá trình Oxi hóa?",
                                                Arrays.asList("Lá phổi", "Gan", "Cơ bắp", "Thận"),
                                                Arrays.asList("Lá phổi"), "Hóa học", "Chương 4: Phản ứng Oxi hóa - Khử",
                                                10, "multiple-choice"));

                List<Question> Chemistry10QuestionsC5 = Arrays.asList(
                                new Question("Năng lượng hóa học được đo bằng đơn vị nào?",
                                                Arrays.asList("Calorie", "Joule", "Kilogram", "Meter"),
                                                Arrays.asList("Joule"), "Hóa học", "Chương 5: Năng lượng hóa học",
                                                10, "multiple-choice"),
                                new Question("Trạng thái hóa học nào có năng lượng cao nhất?",
                                                Arrays.asList("Năng lượng tiềm năng", "Năng lượng cơ học",
                                                                "Năng lượng hóa học", "Năng lượng nhiệt"),
                                                Arrays.asList("Năng lượng tiềm năng"), "Hóa học",
                                                "Chương 5: Năng lượng hóa học",
                                                10, "multiple-choice"));

                List<Question> Chemistry10QuestionsC6 = Arrays.asList(
                                new Question("Tốc độ phản ứng tăng khi nào?",
                                                Arrays.asList("Nhiệt độ giảm", "Sự tiếp xúc",
                                                                "Nồng độ chất phản ứng giảm", "Diện tích bề mặt tăng"),
                                                Arrays.asList("Diện tích bề mặt tăng"), "Hóa học",
                                                "Chương 6: Tốc độ phản ứng",
                                                10, "multiple-choice"),
                                new Question("Cơ chế tăng tốc độ phản ứng chủ yếu thông qua việc gì?",
                                                Arrays.asList("Sự hấp thụ nhiệt", "Sự giảm nhiệt", "Sự giảm khối lượng",
                                                                "Sự tăng diện tích tiếp xúc"),
                                                Arrays.asList("Sự tăng diện tích tiếp xúc"), "Hóa học",
                                                "Chương 6: Tốc độ phản ứng",
                                                10, "multiple-choice"));

                List<Question> Chemistry10QuestionsC7 = Arrays.asList(
                                new Question("Nguyên tố nào sau đây là một nguyên tố nhóm Halogen?",
                                                Arrays.asList("Fluorine", "Nitrogen", "Carbon", "Sulfur"),
                                                Arrays.asList("Fluorine"), "Hóa học",
                                                "Chương 7: Nguyên tố nhóm Halogen",
                                                10, "multiple-choice"),
                                new Question("Nguyên tố Halogen nào thường được sử dụng trong nước tiểu?",
                                                Arrays.asList("Fluorine", "Chlorine", "Bromine", "Iodine"),
                                                Arrays.asList("Iodine"), "Hóa học", "Chương 7: Nguyên tố nhóm Halogen",
                                                10, "multiple-choice"));

                try {
                        questionRepository.saveAll(Chemistry10QuestionsC1);
                        questionRepository.saveAll(Chemistry10QuestionsC2);
                        questionRepository.saveAll(Chemistry10QuestionsC3);
                        questionRepository.saveAll(Chemistry10QuestionsC4);
                        questionRepository.saveAll(Chemistry10QuestionsC5);
                        questionRepository.saveAll(Chemistry10QuestionsC6);
                        questionRepository.saveAll(Chemistry10QuestionsC7);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating Chemistry 10 data");
                }
        }

        public void generatePhysics10() {
                List<Question> Physics10QuestionsC1 = Arrays.asList(
                                new Question("Định nghĩa nào sau đây là đúng về vật lý?",
                                                Arrays.asList("Nghiên cứu về vật chất và năng lượng",
                                                                "Nghiên cứu về cấu trúc nguyên tử",
                                                                "Nghiên cứu về sự sống và sinh học",
                                                                "Nghiên cứu về vũ trụ và sao hỏa"),
                                                Arrays.asList("Nghiên cứu về vật chất và năng lượng"), "Vật lý",
                                                "Chương 1: Mở đầu",
                                                10, "multiple-choice"),
                                new Question("Nguyên lý nào dưới đây không phải là nguyên lý cơ bản của vật lý cổ điển?",
                                                Arrays.asList("Định luật Newton", "Định luật Kepler",
                                                                "Định luật của hạt nhân", "Định luật của áp suất"),
                                                Arrays.asList("Định luật của hạt nhân"), "Vật lý", "Chương 1: Mở đầu",
                                                10, "multiple-choice"));

                List<Question> Physics10QuestionsC2 = Arrays.asList(
                                new Question("Khái niệm nào sau đây đúng về động học?",
                                                Arrays.asList("Nghiên cứu về chuyển động của vật",
                                                                "Nghiên cứu về tác động của lực",
                                                                "Nghiên cứu về năng lượng", "Nghiên cứu về áp suất"),
                                                Arrays.asList("Nghiên cứu về chuyển động của vật"), "Vật lý",
                                                "Chương 2: Động học",
                                                10, "multiple-choice"),
                                new Question("Lực nào làm thay đổi tốc độ vận tốc của vật?",
                                                Arrays.asList("Lực hấp dẫn", "Lực ma sát", "Lực căng", "Lực đàn hồi"),
                                                Arrays.asList("Lực ma sát"), "Vật lý", "Chương 2: Động học",
                                                10, "multiple-choice"));

                List<Question> Physics10QuestionsC3 = Arrays.asList(
                                new Question("Động lực học nghiên cứu về điều gì?",
                                                Arrays.asList("Tính chất của vật chất", "Chuyển động của vật",
                                                                "Áp suất của chất lỏng", "Tốc độ của vật"),
                                                Arrays.asList("Chuyển động của vật"), "Vật lý",
                                                "Chương 3: Động lực học",
                                                10, "multiple-choice"),
                                new Question("Nguyên tố chính ảnh hưởng đến động lực học là gì?",
                                                Arrays.asList("Khối lượng", "Màu sắc", "Nhiệt độ", "Độ dày"),
                                                Arrays.asList("Khối lượng"), "Vật lý", "Chương 3: Động lực học",
                                                10, "multiple-choice"));

                List<Question> Physics10QuestionsC4 = Arrays.asList(
                                new Question("Năng lượng cơ học được tính như thế nào?",
                                                Arrays.asList("E = mc^2", "E = Fd", "E = 1/2 mv^2", "E = P/t"),
                                                Arrays.asList("E = 1/2 mv^2"), "Vật lý",
                                                "Chương 4: Năng lượng, công, công suất",
                                                10, "multiple-choice"),
                                new Question("Công của một lực là gì?",
                                                Arrays.asList("Năng lượng thực hiện công việc",
                                                                "Sức mạnh thực hiện công việc", "Đơn vị năng lượng",
                                                                "Cường độ của lực"),
                                                Arrays.asList("Năng lượng thực hiện công việc"), "Vật lý",
                                                "Chương 4: Năng lượng, công, công suất",
                                                10, "multiple-choice"));

                List<Question> Physics10QuestionsC5 = Arrays.asList(
                                new Question("Động lượng của một vật phụ thuộc vào điều gì?",
                                                Arrays.asList("Khối lượng và vận tốc", "Dung lượng và khối lượng",
                                                                "Độ dày và vận tốc", "Nhiệt độ và áp suất"),
                                                Arrays.asList("Khối lượng và vận tốc"), "Vật lý",
                                                "Chương 5: Động lượng",
                                                10, "multiple-choice"),
                                new Question("Năng lượng cơ học của vật được đo bằng đơn vị gì?",
                                                Arrays.asList("Joule", "Newton", "Watt", "Calorie"),
                                                Arrays.asList("Joule"), "Vật lý", "Chương 5: Động lượng",
                                                10, "multiple-choice"));

                List<Question> Physics10QuestionsC6 = Arrays.asList(
                                new Question("Chuyển động tròn có mấy loại chuyển động chính?",
                                                Arrays.asList("1 loại", "2 loại", "3 loại", "4 loại"),
                                                Arrays.asList("2 loại"), "Vật lý", "Chương 6: Chuyển động tròn",
                                                10, "multiple-choice"),
                                new Question("Lực nào giữ cho một vật chuyển động tròn?",
                                                Arrays.asList("Lực ty định", "Lực ma sát", "Lực hấp dẫn", "Lực căng"),
                                                Arrays.asList("Lực hấp dẫn"), "Vật lý", "Chương 6: Chuyển động tròn",
                                                10, "multiple-choice"));

                List<Question> Physics10QuestionsC7 = Arrays.asList(
                                new Question("Biến dạng của vật rắn xảy ra khi nào?",
                                                Arrays.asList("Khi áp suất tăng", "Khi nhiệt độ giảm",
                                                                "Khi vật bị căng", "Khi lực tác động"),
                                                Arrays.asList("Khi lực tác động"), "Vật lý",
                                                "Chương 7: Biến dạng của vật rắn. Áp suất chất lỏng",
                                                10, "multiple-choice"),
                                new Question("Áp suất của chất lỏng phụ thuộc vào yếu tố nào?",
                                                Arrays.asList("Nhiệt độ và khối lượng", "Khối lượng và thể tích",
                                                                "Nhiệt độ và thể tích", "Độ dày và nhiệt độ"),
                                                Arrays.asList("Nhiệt độ và thể tích"), "Vật lý",
                                                "Chương 7: Biến dạng của vật rắn. Áp suất chất lỏng",
                                                10, "multiple-choice"));

                try {
                        questionRepository.saveAll(Physics10QuestionsC1);
                        questionRepository.saveAll(Physics10QuestionsC2);
                        questionRepository.saveAll(Physics10QuestionsC3);
                        questionRepository.saveAll(Physics10QuestionsC4);
                        questionRepository.saveAll(Physics10QuestionsC5);
                        questionRepository.saveAll(Physics10QuestionsC6);
                        questionRepository.saveAll(Physics10QuestionsC7);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating Physics 10 data");
                }
        }

        public void generateGeography10() {
                List<Question> Geography10QuestionsC1 = Arrays.asList(
                                new Question("Bản đồ địa lý thường biểu diễn thông tin như thế nào?",
                                                Arrays.asList("Thông tin về diện tích",
                                                                "Thông tin về hình dạng và kích thước",
                                                                "Thông tin về dân số", "Thông tin về ngôn ngữ"),
                                                Arrays.asList("Thông tin về hình dạng và kích thước"), "Địa lý",
                                                "Chương 1: Sử dụng bản đồ",
                                                10, "multiple-choice"),
                                new Question("Mục đích chính của bản đồ là gì?",
                                                Arrays.asList("Hiển thị địa hình", "Hiểu biết về văn hóa",
                                                                "Đo lường thời tiết", "Hiển thị thông tin địa lý"),
                                                Arrays.asList("Hiển thị thông tin địa lý"), "Địa lý",
                                                "Chương 1: Sử dụng bản đồ",
                                                10, "multiple-choice"));

                List<Question> Geography10QuestionsC2 = Arrays.asList(
                                new Question("Trái Đất là hành tinh thứ mấy từ Mặt Trời?",
                                                Arrays.asList("Hành tinh thứ nhất", "Hành tinh thứ ba",
                                                                "Hành tinh thứ năm", "Hành tinh thứ bảy"),
                                                Arrays.asList("Hành tinh thứ ba"), "Địa lý", "Chương 2: Trái Đất",
                                                10, "multiple-choice"),
                                new Question("Bề mặt Trái Đất được phân thành mấy lớp chính?",
                                                Arrays.asList("1 lớp", "2 lớp", "3 lớp", "4 lớp"),
                                                Arrays.asList("3 lớp"), "Địa lý", "Chương 2: Trái Đất",
                                                10, "multiple-choice"));

                List<Question> Geography10QuestionsC3 = Arrays.asList(
                                new Question("Thạch quyển là gì?",
                                                Arrays.asList("Lớp đất phong phú khoáng sản", "Lớp đất chứa nước ngầm",
                                                                "Lớp đất lỏng lẻo",
                                                                "Lớp đất có thể can thiệp vào sự sống"),
                                                Arrays.asList("Lớp đất có thể can thiệp vào sự sống"), "Địa lý",
                                                "Chương 3: Thạch quyển",
                                                10, "multiple-choice"),
                                new Question("Quá trình nào tạo ra thạch quyển?",
                                                Arrays.asList("Sóng biển", "Lửa núi", "Mưa và gió",
                                                                "Sự sống của cây cối"),
                                                Arrays.asList("Sóng biển"), "Địa lý", "Chương 3: Thạch quyển",
                                                10, "multiple-choice"));

                List<Question> Geography10QuestionsC4 = Arrays.asList(
                                new Question("Khí quyển là gì?",
                                                Arrays.asList("Lớp đất phong phú khoáng sản",
                                                                "Lớp khí bao quanh Trái Đất", "Lớp đất lỏng lẻo",
                                                                "Lớp đất có thể can thiệp vào sự sống"),
                                                Arrays.asList("Lớp khí bao quanh Trái Đất"), "Địa lý",
                                                "Chương 4: Khí quyển",
                                                10, "multiple-choice"),
                                new Question("Khí quyển quan trọng đối với sự sống trên Trái Đất bởi điều gì?",
                                                Arrays.asList("Bảo vệ khỏi bức xạ", "Tạo ra nước",
                                                                "Bảo vệ khỏi nguy cơ thiên tai", "Giữ nhiệt độ"),
                                                Arrays.asList("Giữ nhiệt độ"), "Địa lý", "Chương 4: Khí quyển",
                                                10, "multiple-choice"));

                List<Question> Geography10QuestionsC5 = Arrays.asList(
                                new Question("Thủy quyển chiếm tỷ lệ bao nhiêu trên bề mặt Trái Đất?",
                                                Arrays.asList("50%", "70%", "90%", "30%"),
                                                Arrays.asList("70%"), "Địa lý", "Chương 5: Thủy quyển",
                                                10, "multiple-choice"),
                                new Question("Thủy quyển có ảnh hưởng như thế nào đối với khí quyển?",
                                                Arrays.asList("Giảm áp suất", "Tạo ra bão", "Chứa lượng lớn hơi nước",
                                                                "Tạo ra bức xạ"),
                                                Arrays.asList("Chứa lượng lớn hơi nước"), "Địa lý",
                                                "Chương 5: Thủy quyển",
                                                10, "multiple-choice"));

                List<Question> Geography10QuestionsC6 = Arrays.asList(
                                new Question("Sinh quyển bao gồm gì?",
                                                Arrays.asList("Các hệ sinh thái", "Hệ cơ sở hạ tầng",
                                                                "Cấu trúc địa chất", "Hệ học thuật"),
                                                Arrays.asList("Các hệ sinh thái"), "Địa lý", "Chương 6: Sinh quyển",
                                                10, "multiple-choice"),
                                new Question("Vì sao sinh quyển quan trọng đối với sự sống?",
                                                Arrays.asList("Cung cấp thực phẩm", "Tạo ra nhiều khí", "Giữ độ ẩm",
                                                                "Tạo ra đất màu mỡ"),
                                                Arrays.asList("Cung cấp thực phẩm"), "Địa lý", "Chương 6: Sinh quyển",
                                                10, "multiple-choice"));

                List<Question> Geography10QuestionsC7 = Arrays.asList(
                                new Question("Quy luật nào giải thích sự di chuyển của các tảng lục địa?",
                                                Arrays.asList("Quy luật hấp dẫn", "Quy luật vận tốc", "Quy luật đĩa kỳ",
                                                                "Quy luật khí hậu"),
                                                Arrays.asList("Quy luật đĩa kỳ"), "Địa lý",
                                                "Chương 7: Một số quy luật của vỏ địa lí",
                                                10, "multiple-choice"),
                                new Question("Hiện tượng gì gây ra động đất và núi lửa?",
                                                Arrays.asList("Sóng biển", "Chuyển động đĩa kỳ", "Sự thay đổi nhiệt độ",
                                                                "Tác động của rác thải"),
                                                Arrays.asList("Chuyển động đĩa kỳ"), "Địa lý",
                                                "Chương 7: Một số quy luật của vỏ địa lí",
                                                10, "multiple-choice"));

                try {
                        questionRepository.saveAll(Geography10QuestionsC1);
                        questionRepository.saveAll(Geography10QuestionsC2);
                        questionRepository.saveAll(Geography10QuestionsC3);
                        questionRepository.saveAll(Geography10QuestionsC4);
                        questionRepository.saveAll(Geography10QuestionsC5);
                        questionRepository.saveAll(Geography10QuestionsC6);
                        questionRepository.saveAll(Geography10QuestionsC7);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating Geography 10 data");
                }
        }

        public void generateHistory10() {
                List<Question> History10QuestionsC1 = Arrays.asList(
                                new Question("Sử học nghiên cứu về điều gì?",
                                                Arrays.asList("Các sự kiện trong quá khứ", "Các phong tục tập quán",
                                                                "Câu chuyện thần thoại", "Những biểu hiện văn hóa"),
                                                Arrays.asList("Các sự kiện trong quá khứ"), "Lịch sử",
                                                "Chương 1: Lịch sử và sử học",
                                                10, "multiple-choice"),
                                new Question("Sử học đóng vai trò quan trọng như thế nào đối với con người?",
                                                Arrays.asList("Hiểu biết về quá khứ", "Tạo nền tảng cho tương lai",
                                                                "Phát triển văn hóa", "Giải trí"),
                                                Arrays.asList("Hiểu biết về quá khứ"), "Lịch sử",
                                                "Chương 1: Lịch sử và sử học",
                                                10, "multiple-choice"));

                List<Question> History10QuestionsC2 = Arrays.asList(
                                new Question("Vai trò chính của sử học là gì?",
                                                Arrays.asList("Ghi chép sự kiện quan trọng", "Phê phán lịch sử",
                                                                "Xây dựng tư duy", "Đánh giá văn minh"),
                                                Arrays.asList("Xây dựng tư duy"), "Lịch sử",
                                                "Chương 2: Vai trò của sử học",
                                                10, "multiple-choice"),
                                new Question("Sử học giúp con người như thế nào trong cuộc sống hàng ngày?",
                                                Arrays.asList("Hiểu biết lịch sử gia đình",
                                                                "Hiểu biết văn minh thế giới",
                                                                "Phát triển kỹ năng sống",
                                                                "Thành công trong sự nghiệp"),
                                                Arrays.asList("Hiểu biết văn minh thế giới"), "Lịch sử",
                                                "Chương 2: Vai trò của sử học",
                                                10, "multiple-choice"));

                List<Question> History10QuestionsC3 = Arrays.asList(
                                new Question("Nền văn minh cổ đại nổi tiếng nào được xem là nền tảng của văn minh phương Tây?",
                                                Arrays.asList("Hy Lạp cổ đại", "Ai Cập cổ đại", "La Mã cổ đại",
                                                                "Sumer cổ đại"),
                                                Arrays.asList("Hy Lạp cổ đại"), "Lịch sử",
                                                "Chương 3: Một số nền văn minh thế giới thời kì cổ - trung đại",
                                                10, "multiple-choice"),
                                new Question("Nền văn minh nào có sự phát triển vượt trội trong khoa học, triết học và nghệ thuật?",
                                                Arrays.asList("Ai Cập cổ đại", "Trung Quốc cổ đại", "Hy Lạp cổ đại",
                                                                "Babylon cổ đại"),
                                                Arrays.asList("Hy Lạp cổ đại"), "Lịch sử",
                                                "Chương 3: Một số nền văn minh thế giới thời kì cổ - trung đại",
                                                10, "multiple-choice"));

                List<Question> History10QuestionsC4 = Arrays.asList(
                                new Question("Cuộc cách mạng công nghiệp làm thay đổi gì trong xã hội?",
                                                Arrays.asList("Phát triển nông nghiệp", "Tăng trưởng dân số",
                                                                "Đổi mới kỹ thuật", "Thúc đẩy nghệ thuật"),
                                                Arrays.asList("Đổi mới kỹ thuật"), "Lịch sử",
                                                "Chương 4: Các cuộc cách mạng công nghiệp trong lịch sử thế giới",
                                                10, "multiple-choice"),
                                new Question("Cuộc cách mạng công nghiệp bắt đầu ở đâu?",
                                                Arrays.asList("Anh", "Pháp", "Đức", "Mỹ"),
                                                Arrays.asList("Anh"), "Lịch sử",
                                                "Chương 4: Các cuộc cách mạng công nghiệp trong lịch sử thế giới",
                                                10, "multiple-choice"));

                List<Question> History10QuestionsC5 = Arrays.asList(
                                new Question("Văn minh Đông Nam Á có ảnh hưởng lớn từ nền văn minh nào?",
                                                Arrays.asList("Trung Quốc", "Nhật Bản", "Ấn Độ", "Thái Lan"),
                                                Arrays.asList("Trung Quốc"), "Lịch sử", "Chương 5: Văn minh Đông Nam Á",
                                                10, "multiple-choice"),
                                new Question("Loại hình nền văn minh nào phổ biến tại Đông Nam Á?",
                                                Arrays.asList("Nông nghiệp", "Đô thị hóa", "Thủ công nghiệp",
                                                                "Chăn nuôi"),
                                                Arrays.asList("Nông nghiệp"), "Lịch sử",
                                                "Chương 5: Văn minh Đông Nam Á",
                                                10, "multiple-choice"));

                List<Question> History10QuestionsC6 = Arrays.asList(
                                new Question("Nền văn minh nào thể hiện sự phát triển trong kiến trúc, nghệ thuật và văn hóa tại Việt Nam trước năm 1858?",
                                                Arrays.asList("Lê sơ", "Hồng Bàng", "Trần", "Đinh - Tiền Lê"),
                                                Arrays.asList("Đinh - Tiền Lê"), "Lịch sử",
                                                "Chương 6: Một số nền văn minh trên đất nước Việt Nam (trước năm 1858)",
                                                10, "multiple-choice"),
                                new Question("Nền văn minh nào là thời kỳ hoàng kim của văn hóa Việt Nam?",
                                                Arrays.asList("Hồng Bàng", "Lê sơ", "Trần", "Đinh - Tiền Lê"),
                                                Arrays.asList("Lê sơ"), "Lịch sử",
                                                "Chương 6: Một số nền văn minh trên đất nước Việt Nam (trước năm 1858)",
                                                10, "multiple-choice"));

                List<Question> History10QuestionsC7 = Arrays.asList(
                                new Question("Cộng đồng các dân tộc Việt Nam được xây dựng dựa trên nguyên tắc gì?",
                                                Arrays.asList("Tự chủ dân tộc", "Phong kiến", "Quân chủ", "Cộng sản"),
                                                Arrays.asList("Tự chủ dân tộc"), "Lịch sử",
                                                "Chương 7: Cộng đồng các dân tộc Việt Nam",
                                                10, "multiple-choice"),
                                new Question("Điều gì tạo nên sự đa dạng văn hóa của cộng đồng dân tộc Việt Nam?",
                                                Arrays.asList("Ngôn ngữ", "Truyền thống", "Văn hóa", "Tôn giáo"),
                                                Arrays.asList("Ngôn ngữ"), "Lịch sử",
                                                "Chương 7: Cộng đồng các dân tộc Việt Nam",
                                                10, "multiple-choice"));
                try {
                        questionRepository.saveAll(History10QuestionsC1);
                        questionRepository.saveAll(History10QuestionsC2);
                        questionRepository.saveAll(History10QuestionsC3);
                        questionRepository.saveAll(History10QuestionsC4);
                        questionRepository.saveAll(History10QuestionsC5);
                        questionRepository.saveAll(History10QuestionsC6);
                        questionRepository.saveAll(History10QuestionsC7);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating History 10 data");
                }
        }

        public void generateEnglish10() {

                List<Question> English10QuestionsC1 = Arrays.asList(
                                new Question("What does 'family life' primarily refer to?",
                                                Arrays.asList("Daily routine", "Interactions with friends",
                                                                "Activities at school", "Interactions within a family"),
                                                Arrays.asList("Interactions within a family"), "Tiếng Anh",
                                                "Chương 1: Family life",
                                                10, "multiple-choice"),
                                new Question("Why is family life significant?",
                                                Arrays.asList("Building friendships", "Developing social skills",
                                                                "Cultural understanding",
                                                                "Establishing a supportive environment"),
                                                Arrays.asList("Establishing a supportive environment"), "Tiếng Anh",
                                                "Chương 1: Family life",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC2 = Arrays.asList(
                                new Question("How do humans impact the environment?",
                                                Arrays.asList("Positively", "Negatively", "Not at all",
                                                                "In a neutral way"),
                                                Arrays.asList("Negatively"), "Tiếng Anh",
                                                "Chương 2: Humans and the environment",
                                                10, "multiple-choice"),
                                new Question("What's one way to reduce environmental impact?",
                                                Arrays.asList("Consuming more resources",
                                                                "Implementing recycling programs",
                                                                "Disregarding pollution",
                                                                "Avoiding conservation efforts"),
                                                Arrays.asList("Implementing recycling programs"), "Tiếng Anh",
                                                "Chương 2: Humans and the environment",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC3 = Arrays.asList(
                                new Question("What is the primary focus of the chapter 'Music'?",
                                                Arrays.asList("History of music", "Different instruments",
                                                                "Musical genres",
                                                                "Appreciation and understanding of music"),
                                                Arrays.asList("Appreciation and understanding of music"), "Tiếng Anh",
                                                "Chương 3: Music",
                                                10, "multiple-choice"),
                                new Question("Why is music considered a universal language?",
                                                Arrays.asList("It requires no instruments", "It's understood globally",
                                                                "It's an ancient art form", "It's easy to learn"),
                                                Arrays.asList("It's understood globally"), "Tiếng Anh",
                                                "Chương 3: Music",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC4 = Arrays.asList(
                                new Question("How can individuals contribute to a better community?",
                                                Arrays.asList("Ignoring community issues", "Being self-centered",
                                                                "Volunteering and helping others",
                                                                "Promoting conflict"),
                                                Arrays.asList("Volunteering and helping others"), "Tiếng Anh",
                                                "Chương 4: For a better community",
                                                10, "multiple-choice"),
                                new Question("What are some benefits of community engagement?",
                                                Arrays.asList("Isolation", "Building connections",
                                                                "Decreased social awareness",
                                                                "Reduced sense of belonging"),
                                                Arrays.asList("Building connections"), "Tiếng Anh",
                                                "Chương 4: For a better community",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC5 = Arrays.asList(
                                new Question("What drives human progress in terms of inventions?",
                                                Arrays.asList("Curiosity and creativity", "Fear of change",
                                                                "Avoidance of technology", "Disregard for innovation"),
                                                Arrays.asList("Curiosity and creativity"), "Tiếng Anh",
                                                "Chương 5: Inventions",
                                                10, "multiple-choice"),
                                new Question("How do inventions impact society?",
                                                Arrays.asList("They hinder progress", "They have no effect",
                                                                "They drive progress and change",
                                                                "They cause regression"),
                                                Arrays.asList("They drive progress and change"), "Tiếng Anh",
                                                "Chương 5: Inventions",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC6 = Arrays.asList(
                                new Question("What is the essence of the chapter 'Gender equality'?",
                                                Arrays.asList("Historical perspectives", "Contemporary issues",
                                                                "Promoting equality", "Biological differences"),
                                                Arrays.asList("Promoting equality"), "Tiếng Anh",
                                                "Chương 6: Gender equality",
                                                10, "multiple-choice"),
                                new Question("Why is gender equality essential in society?",
                                                Arrays.asList("It promotes division", "It hampers progress",
                                                                "It fosters inclusivity", "It reinforces stereotypes"),
                                                Arrays.asList("It fosters inclusivity"), "Tiếng Anh",
                                                "Chương 6: Gender equality",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC7 = Arrays.asList(
                                new Question("What does the chapter 'Viet Nam and international organisations' emphasize?",
                                                Arrays.asList("Global partnerships", "Local culture",
                                                                "Political conflicts", "Isolationism"),
                                                Arrays.asList("Global partnerships"), "Tiếng Anh",
                                                "Chương 7: Viet Nam and international organisations",
                                                10, "multiple-choice"),
                                new Question("Why is involvement in international organisations important?",
                                                Arrays.asList("To limit communication", "To foster cooperation",
                                                                "To promote division", "To hinder progress"),
                                                Arrays.asList("To foster cooperation"), "Tiếng Anh",
                                                "Chương 7: Viet Nam and international organisations",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC8 = Arrays.asList(
                                new Question("What's the focus of 'New ways to learn'?",
                                                Arrays.asList("Traditional education", "Innovative learning methods",
                                                                "Memorization", "Rote learning"),
                                                Arrays.asList("Innovative learning methods"), "Tiếng Anh",
                                                "Chương 8: New ways to learn",
                                                10, "multiple-choice"),
                                new Question("Why are new learning methods gaining popularity?",
                                                Arrays.asList("They restrict creativity", "They limit exploration",
                                                                "They cater to diverse needs",
                                                                "They discourage engagement"),
                                                Arrays.asList("They cater to diverse needs"), "Tiếng Anh",
                                                "Chương 8: New ways to learn",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC9 = Arrays.asList(
                                new Question("What's the primary goal of 'Protecting the environment'?",
                                                Arrays.asList("Encouraging pollution", "Raising awareness",
                                                                "Ignoring conservation efforts",
                                                                "Limiting resource use"),
                                                Arrays.asList("Raising awareness"), "Tiếng Anh",
                                                "Chương 9: Protecting the environment",
                                                10, "multiple-choice"),
                                new Question("Why is environmental protection crucial?",
                                                Arrays.asList("It promotes destruction", "It ensures sustainability",
                                                                "It hampers progress", "It fosters negligence"),
                                                Arrays.asList("It ensures sustainability"), "Tiếng Anh",
                                                "Chương 9: Protecting the environment",
                                                10, "multiple-choice"));

                List<Question> English10QuestionsC10 = Arrays.asList(
                                new Question("What is the focus of 'Ecotourism'?",
                                                Arrays.asList("Mass tourism", "Sustainable travel",
                                                                "Irresponsible travel", "Damaging environments"),
                                                Arrays.asList("Sustainable travel"), "Tiếng Anh",
                                                "Chương 10: Ecotourism",
                                                10, "multiple-choice"),
                                new Question("Why is ecotourism gaining attention?",
                                                Arrays.asList("It exploits nature", "It disregards local cultures",
                                                                "It supports conservation", "It promotes pollution"),
                                                Arrays.asList("It supports conservation"), "Tiếng Anh",
                                                "Chương 10: Ecotourism",
                                                10, "multiple-choice"));

                try {
                        questionRepository.saveAll(English10QuestionsC1);
                        questionRepository.saveAll(English10QuestionsC2);
                        questionRepository.saveAll(English10QuestionsC3);
                        questionRepository.saveAll(English10QuestionsC4);
                        questionRepository.saveAll(English10QuestionsC5);
                        questionRepository.saveAll(English10QuestionsC6);
                        questionRepository.saveAll(English10QuestionsC7);
                        questionRepository.saveAll(English10QuestionsC8);
                        questionRepository.saveAll(English10QuestionsC9);
                        questionRepository.saveAll(English10QuestionsC10);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating History 10 data");
                }
        }

        public void generateLiterature10() {
                List<Question> Literature10QuestionsC1 = Arrays.asList(
                                new Question("Điều gì khiến cho việc kể chuyện thu hút?",
                                                Arrays.asList("Cốt truyện phức tạp", "Phát triển nhân vật",
                                                                "Cách diễn đạt lôi cuốn", "Kết thúc dễ đoán"),
                                                Arrays.asList("Cách diễn đạt lôi cuốn"), "Ngữ văn",
                                                "Chương 1: Sức hấp dẫn của truyện kể",
                                                10, "multiple-choice"),
                                new Question("Tại sao truyền thống truyền miệng có ý nghĩa quan trọng trong việc kể chuyện?",
                                                Arrays.asList("Hạn chế sáng tạo", "Bảo tồn văn hóa",
                                                                "Ngăn chặn biểu đạt", "Khuyến khích cô lập"),
                                                Arrays.asList("Bảo tồn văn hóa"), "Ngữ văn",
                                                "Chương 1: Sức hấp dẫn của truyện kể",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC2 = Arrays.asList(
                                new Question("Điều gì phân biệt vẻ đẹp của thơ ca?",
                                                Arrays.asList("Cấu trúc cứng nhắc", "Biểu hiện cảm xúc",
                                                                "Chủ đề dễ đoán", "Ngôn ngữ đơn giản"),
                                                Arrays.asList("Biểu hiện cảm xúc"), "Ngữ văn",
                                                "Chương 2: Vẻ đẹp của thơ ca",
                                                10, "multiple-choice"),
                                new Question("Tại sao hình ảnh quan trọng trong thơ ca?",
                                                Arrays.asList("Hạn chế hiểu biết", "Gợi cảm xúc", "Hạn chế sáng tạo",
                                                                "Kìm hãm ý tưởng"),
                                                Arrays.asList("Gợi cảm xúc"), "Ngữ văn", "Chương 2: Vẻ đẹp của thơ ca",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC3 = Arrays.asList(
                                new Question("Nghệ thuật thuyết phục trong văn nghị luận tập trung vào điều gì?",
                                                Arrays.asList("Biện minh logic", "Biểu đạt cảm xúc",
                                                                "Chứng minh khoa học", "Phát ngôn cá nhân"),
                                                Arrays.asList("Biện minh logic"), "Ngữ văn",
                                                "Chương 3: Nghệ thuật thuyết phục trong văn nghị luận",
                                                10, "multiple-choice"),
                                new Question("Tại sao việc thuyết phục quan trọng trong văn nghị luận?",
                                                Arrays.asList("Để ngăn chặn biểu đạt", "Để khuyến khích tranh luận",
                                                                "Để giữ bí mật", "Để tạo xung đột"),
                                                Arrays.asList("Để khuyến khích tranh luận"), "Ngữ văn",
                                                "Chương 3: Nghệ thuật thuyết phục trong văn nghị luận",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC4 = Arrays.asList(
                                new Question("Sức sống của sử thi thể hiện qua điều gì?",
                                                Arrays.asList("Ngôn ngữ phong phú", "Cốt truyện hấp dẫn",
                                                                "Biện minh logic", "Cảm xúc riêng biệt"),
                                                Arrays.asList("Ngôn ngữ phong phú"), "Ngữ văn",
                                                "Chương 4: Sức sống của sử thi",
                                                10, "multiple-choice"),
                                new Question("Tại sao sử thi được coi là thể loại văn học quan trọng?",
                                                Arrays.asList("Vì nó thiếu sức hấp dẫn", "Vì nó mang giá trị lịch sử",
                                                                "Vì nó không phản ánh văn hóa", "Vì nó kém phổ biến"),
                                                Arrays.asList("Vì nó mang giá trị lịch sử"), "Ngữ văn",
                                                "Chương 4: Sức sống của sử thi",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC5 = Arrays.asList(
                                new Question("Tích trò sân khấu dân gian giúp gì cho cộng đồng?",
                                                Arrays.asList("Giáo dục", "Gây hỗn loạn", "Góp phần tiến bộ",
                                                                "Phá hoại văn hóa"),
                                                Arrays.asList("Giáo dục"), "Ngữ văn",
                                                "Chương 5: Tích trò sân khấu dân gian",
                                                10, "multiple-choice"),
                                new Question("Tại sao nghệ thuật sân khấu dân gian quan trọng?",
                                                Arrays.asList("Vì nó tạo xung đột", "Vì nó không gây ấn tượng",
                                                                "Vì nó góp phần vào văn hóa", "Vì nó không giáo dục"),
                                                Arrays.asList("Vì nó góp phần vào văn hóa"), "Ngữ văn",
                                                "Chương 5: Tích trò sân khấu dân gian",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC6 = Arrays.asList(
                                new Question("Trong bài văn 'Nguyễn Trãi - Dành còn để trợ dân này', tác giả chủ yếu diễn đạt điều gì?",
                                                Arrays.asList("Sự cảm thông", "Kỷ luật quân sự", "Tài năng chính trị",
                                                                "Tư tưởng triết học"),
                                                Arrays.asList("Sự cảm thông"), "Ngữ văn",
                                                "Chương 6: Nguyễn Trãi - Dành còn để trợ dân này",
                                                10, "multiple-choice"),
                                new Question("Tại sao việc hiểu biết về Nguyễn Trãi quan trọng?",
                                                Arrays.asList("Vì không có giá trị lịch sử", "Vì nó không gây ấn tượng",
                                                                "Vì nó mở rộng hiểu biết", "Vì nó kém phổ biến"),
                                                Arrays.asList("Vì nó mở rộng hiểu biết"), "Ngữ văn",
                                                "Chương 6: Nguyễn Trãi - Dành còn để trợ dân này",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC7 = Arrays.asList(
                                new Question("Quyền năng của người kể chuyện là gì trong văn chương?",
                                                Arrays.asList("Khuyến khích sự tranh luận", "Mở rộng tầm hiểu biết",
                                                                "Gây sự tò mò", "Giữ bí mật"),
                                                Arrays.asList("Mở rộng tầm hiểu biết"), "Ngữ văn",
                                                "Chương 7: Quyền năng của người kể chuyện",
                                                10, "multiple-choice"),
                                new Question("Tại sao việc nghe kể chuyện có ý nghĩa?",
                                                Arrays.asList("Vì nó làm mất thời gian", "Vì nó không giúp hiểu biết",
                                                                "Vì nó góp phần vào văn hóa",
                                                                "Vì nó không kích thích trí tò mò"),
                                                Arrays.asList("Vì nó góp phần vào văn hóa"), "Ngữ văn",
                                                "Chương 7: Quyền năng của người kể chuyện",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC8 = Arrays.asList(
                                new Question("Thế giới đa dạng của thông tin có ảnh hưởng như thế nào đến xã hội?",
                                                Arrays.asList("Giúp mở rộng kiến thức", "Gây sự chán chường",
                                                                "Hạn chế trí tò mò", "Phá hoại văn hóa"),
                                                Arrays.asList("Giúp mở rộng kiến thức"), "Ngữ văn",
                                                "Chương 8: Thế giới đa dạng của thông tin",
                                                10, "multiple-choice"),
                                new Question("Tại sao việc hiểu biết thông tin đa dạng quan trọng?",
                                                Arrays.asList("Vì nó không giúp mở rộng kiến thức",
                                                                "Vì nó kích thích sự hiểu biết",
                                                                "Vì nó không quan trọng", "Vì nó không ảnh hưởng"),
                                                Arrays.asList("Vì nó kích thích sự hiểu biết"), "Ngữ văn",
                                                "Chương 8: Thế giới đa dạng của thông tin",
                                                10, "multiple-choice"));

                List<Question> Literature10QuestionsC9 = Arrays.asList(
                                new Question("Hành trang cuộc sống hướng đến điều gì?",
                                                Arrays.asList("Hỗ trợ học tập", "Phát triển kỹ năng", "Bảo tồn văn hóa",
                                                                "Hủy hoại môi trường"),
                                                Arrays.asList("Phát triển kỹ năng"), "Ngữ văn",
                                                "Chương 9: Hành trang cuộc sống",
                                                10, "multiple-choice"),
                                new Question("Tại sao việc sẵn sàng cho cuộc sống quan trọng?",
                                                Arrays.asList("Vì nó không quan trọng",
                                                                "Vì nó không phát triển kỹ năng", "Vì nó giúp chuẩn bị",
                                                                "Vì nó không giúp tạo điều kiện"),
                                                Arrays.asList("Vì nó giúp chuẩn bị"), "Ngữ văn",
                                                "Chương 9: Hành trang cuộc sống",
                                                10, "multiple-choice"));

                try {
                        questionRepository.saveAll(Literature10QuestionsC1);
                        questionRepository.saveAll(Literature10QuestionsC2);
                        questionRepository.saveAll(Literature10QuestionsC3);
                        questionRepository.saveAll(Literature10QuestionsC4);
                        questionRepository.saveAll(Literature10QuestionsC5);
                        questionRepository.saveAll(Literature10QuestionsC6);
                        questionRepository.saveAll(Literature10QuestionsC7);
                        questionRepository.saveAll(Literature10QuestionsC8);
                        questionRepository.saveAll(Literature10QuestionsC9);

                } catch (Exception e) {
                        throw new RuntimeException("Error generating History 10 data");
                }
        }

        public void dataGenerate() {
                // Grade 10
                generateBiology10();
                generateChemistry10();
                generateEnglish10();
                generateGeography10();
                generateHistory10();
                generateLiterature10();
                generateMath10();
                generatePhysics10();

                // Grade 11
                // generateBiology10();
                // generateChemistry10();
                // generateEnglish10();
                // generateGeography10();
                // generateHistory10();
                // generateLiterature10();
                // generateMath10();
                // generatePhysics10();
        }
}
