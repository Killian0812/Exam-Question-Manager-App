package com.killian.SpringBoot.ExamApp.database;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.killian.SpringBoot.ExamApp.repositories.ExamRepository;
import com.killian.SpringBoot.ExamApp.repositories.QuestionRepository;

// Connect to mysql with JPA
/*
docker run -d --rm --name mysql-springboot-exam \
-e MYSQL_ROOT_PASSWORD=123456 \
-e MYSQL_USER=killian \
-e MYSQL_PASSWORD=081203 \
-e MYSQL_DATABASE=my_db \
-p 3309:3306 \
--volume mysql-springboot-exam-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u killian -p
*/
@Configuration
public class Database {

    public static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository, ExamRepository examRepository) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {

                // fake data inserting
                // Question questionA = new Question(
                //         "Which is the non-primitive data type?",
                //         Arrays.asList("int", "short", "double", "String"), 3, "IT", "Easy");
                // Question questionB = new Question("Range of int data type?",
                //         Arrays.asList("0...255", "-32768...32768", "-128...127", "0...65535"), 1, "IT", "Easy");
                // Question questionC = new Question(
                //         "Who is the 26th USA President?",
                //         Arrays.asList("Theodore Roosevelt", "Barack Obama", "Donald Trump", "Joe Biden"), 0, "History",
                //         "Medium");
                // Question questionD = new Question("Maximum number of solution of 2-degree equation?",
                //         Arrays.asList("4", "1", "2", "0"), 2, "Math", "Easy");
                try {
                    // questionRepository.save(questionA);
                    // questionRepository.save(questionB);
                    // questionRepository.save(questionC);
                    // questionRepository.save(questionD);
                    logger.info("Inserted data successfully");
                } catch (Exception e) {
                    logger.info("Data duplicated");
                }
            }

        };
    }
}
