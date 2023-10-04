package com.killian.SpringBoot.ExamApp.models;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

// A POJO 
@Entity
@Table(name = "tblQuestion")
public class Question {
    // this is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // validate = constraint
    @Column(nullable = false, unique = true, length = 300)
    private String text;

    @ElementCollection
    @CollectionTable(name = "choices", joinColumns = @JoinColumn(name = "question_id"))
    private List<String> choices;

    private int correctAnswerID;

    private String subject;

    private String difficulty;

    public Question() {
    }

    public Question(Long id, String text, List<String> choices, int correctAnswerID) {
        this.id = id;
        this.text = text;
        this.choices = choices;
        this.correctAnswerID = correctAnswerID;
    }

    public Question(String text, List<String> choices, int correctAnswerID) {
        this.text = text;
        this.choices = choices;
        this.correctAnswerID = correctAnswerID;
    }

    public Question(String text, List<String> choices, int correctAnswerID, String subject, String difficulty) {
        this.text = text;
        this.choices = choices;
        this.correctAnswerID = correctAnswerID;
        this.subject = subject;
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setChoicesByOrder(String newChoice, int idx) {
        this.choices.set(idx, newChoice);
    }

    public int getCorrectAnswerID() {
        return correctAnswerID;
    }

    public void setCorrectAnswerID(int correctAnswerID) {
        this.correctAnswerID = correctAnswerID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        return false;

    }
}
