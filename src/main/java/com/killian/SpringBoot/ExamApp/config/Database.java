package com.killian.SpringBoot.ExamApp.config;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
*/
/* 
mysql -h localhost -P 3309 --protocol=tcp -u killian -p
*/

@Configuration
public class Database {

    public static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Autowired
    DataGenerator dataGenerator;

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository, ExamRepository examRepository) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                try {
                    dataGenerator.dataGenerate();
                    logger.info("Data generated");
                } catch (Exception e) {
                    logger.info("Data duplicated");
                }
            }

        };
    }
}
