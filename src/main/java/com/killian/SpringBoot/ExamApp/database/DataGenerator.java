package com.killian.SpringBoot.ExamApp.database;

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

        public List<Question> easyITQuestions = Arrays.asList(
                        new Question(
                                        "Which is the non-primitive data type?",
                                        Arrays.asList("int", "short", "double", "String"),
                                        3,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What does 'HTTP' stand for?",
                                        Arrays.asList("Hypertext Transfer Protocol", "High Tech Programming",
                                                        "Hyper Transfer Text Protocol", "Home Tool Programming"),
                                        0,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What is the main function of an operating system?",
                                        Arrays.asList("Display graphics", "Play music",
                                                        "Manage hardware and software resources",
                                                        "Create documents"),
                                        2,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "Which programming language is known for its simplicity and readability?",
                                        Arrays.asList("Java", "C++", "Python", "JavaScript"),
                                        2,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What does 'SQL' stand for?",
                                        Arrays.asList("Super Quick Language", "Structured Query Language",
                                                        "Simple Question Language",
                                                        "Standard Query Language"),
                                        1,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What is the file extension for a Java source code file?",
                                        Arrays.asList(".txt", ".java", ".exe", ".html"),
                                        1,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What does 'HTML' stand for?",
                                        Arrays.asList("Hyper Text Markup Language", "Highly Technical Modern Language",
                                                        "Home Tool Management Language",
                                                        "Hypertext Transfer Markup Language"),
                                        0,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What is the primary function of a firewall?",
                                        Arrays.asList("Send emails", "Block unauthorized access", "Install software",
                                                        "Print documents"),
                                        1,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "What is the cloud computing service provided by Amazon?",
                                        Arrays.asList("Azure", "Google Cloud", "IBM Cloud",
                                                        "Amazon Web Services (AWS)"),
                                        3,
                                        "IT",
                                        "Easy"),
                        new Question(
                                        "Which data structure follows the Last-In-First-Out (LIFO) principle?",
                                        Arrays.asList("Queue", "Stack", "Array", "Linked List"),
                                        1,
                                        "IT",
                                        "Easy"));

        List<Question> mediumITQuestions = Arrays.asList(
                        new Question(
                                        "What is the OSI model used for in networking?",
                                        Arrays.asList("Order of Service Information", "Open Source Integration",
                                                        "Operational System Interface",
                                                        "Network communication framework"),
                                        3,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "Which programming language is used for building Android applications?",
                                        Arrays.asList("Java", "Python", "C++", "JavaScript"),
                                        0,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "What is the primary function of a router in a computer network?",
                                        Arrays.asList("Block viruses", "Connect printers",
                                                        "Forward data packets between computer networks",
                                                        "Play video games"),
                                        2,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "What does 'URL' stand for?",
                                        Arrays.asList("Universal Resource Locator", "Unified Response Language",
                                                        "User Registration Link",
                                                        "Underlying Router Language"),
                                        0,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "Which data structure is typically used for implementing a queue?",
                                        Arrays.asList("Array", "Stack", "Linked List", "Heap"),
                                        2,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "What is the purpose of the 'git clone' command in version control systems?",
                                        Arrays.asList("Create a new repository", "Commit changes",
                                                        "Create a local copy of a remote repository",
                                                        "Delete a repository"),
                                        2,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "What is the role of an index in a database?",
                                        Arrays.asList("Store primary data", "Sort data alphabetically",
                                                        "Improve query performance by providing quick access to data",
                                                        "Backup data"),
                                        2,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "Which web browser is developed by Mozilla?",
                                        Arrays.asList("Internet Explorer", "Safari", "Chrome", "Firefox"),
                                        3,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "In programming, what is a 'loop' used for?",
                                        Arrays.asList("Print text", "Execute code once", "Perform repetitive tasks",
                                                        "Create a new function"),
                                        2,
                                        "IT",
                                        "Medium"),
                        new Question(
                                        "Which programming language is known for its use in web development and server-side scripting?",
                                        Arrays.asList("Java", "C#", "PHP", "Swift"),
                                        2,
                                        "IT",
                                        "Medium"));

        List<Question> hardITQuestions = Arrays.asList(
                        new Question(
                                        "What does the acronym 'SQL' stand for in the context of databases?",
                                        Arrays.asList("Structured Query Language", "System Query Language",
                                                        "Sequential Query Language",
                                                        "Static Query Language"),
                                        0,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "What is the purpose of an 'API' in software development?",
                                        Arrays.asList("Application Programming Interface",
                                                        "Advanced Program Integration",
                                                        "Automated Program Interface",
                                                        "Application Process Interaction"),
                                        0,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "In computer science, what does 'DNS' stand for?",
                                        Arrays.asList("Domain Name System", "Data Network Server",
                                                        "Dynamic Network Service",
                                                        "Digital Network Standard"),
                                        0,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "Which programming language is primarily used for artificial intelligence and machine learning?",
                                        Arrays.asList("Java", "Python", "C++", "Ruby"),
                                        1,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "What is 'HTTPS' used for on the internet?",
                                        Arrays.asList("Hypertext Transfer Protocol Secure",
                                                        "Hypertext Transfer Protocol Simple",
                                                        "High-Efficiency Transport Protocol", "Hyperlink Text Sharing"),
                                        0,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "In networking, what is the purpose of a 'firewall'?",
                                        Arrays.asList("Prevent unauthorized access and protect against cyber threats",
                                                        "Speed up internet connection", "Serve as a network switch",
                                                        "Manage email communication"),
                                        0,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "What is the term for a malicious software that demands a ransom from its victims?",
                                        Arrays.asList("Spyware", "Virus", "Ransomware", "Adware"),
                                        2,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "Which data structure is typically used for implementing a priority queue?",
                                        Arrays.asList("Array", "Stack", "Linked List", "Heap"),
                                        3,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "What is the primary role of a 'server' in a client-server network architecture?",
                                        Arrays.asList("Execute client applications", "Store client data",
                                                        "Handle client requests and provide services",
                                                        "Act as a client firewall"),
                                        2,
                                        "IT",
                                        "Hard"),
                        new Question(
                                        "Which programming language was developed at Bell Labs and is known for its use in systems programming?",
                                        Arrays.asList("Java", "Python", "C", "Ruby"),
                                        2,
                                        "IT",
                                        "Hard"));

        public void dataGenerate() {
                try {
                        questionRepository.saveAll(easyITQuestions);
                        questionRepository.saveAll(mediumITQuestions);
                        questionRepository.saveAll(hardITQuestions);
                } catch (Exception e) {
                        throw new RuntimeException("Error generating data");
                }
        }

}
