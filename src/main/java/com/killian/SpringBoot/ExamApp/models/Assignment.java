package com.killian.SpringBoot.ExamApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblAssignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<String> students;

    private List<Double> scores;

    private int duration;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public Assignment() {
    }

    public Assignment(String name, int duration, Date deadline, Exam exam) {
        this.name = name;
        this.duration = duration;
        this.deadline = deadline;
        this.exam = exam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void addStudentScore(String student, double score) {
        students.add(student);
        scores.add(score);
    }
}
