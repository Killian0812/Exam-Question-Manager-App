package com.killian.SpringBoot.ExamApp.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

// A POJO 
@Entity
@Table(name = "tblExam")
public class Exam {
    // this is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private int grade;

    @Column(unique = true)
    private String name;

    private int examCode;

    @ManyToMany
    @JoinTable(name = "exam", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;

    public Exam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getExamCode() {
        return examCode;
    }

    public void setExamCode(int examCode) {
        this.examCode = examCode;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return false;

    }
}
