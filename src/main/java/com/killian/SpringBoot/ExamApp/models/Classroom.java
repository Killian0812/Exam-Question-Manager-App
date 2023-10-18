package com.killian.SpringBoot.ExamApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tblClassroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String subject;

    private String grade;

    private String teacher;

    private List<String> students;

    @OneToMany
    @JoinTable(name = "classroom", joinColumns = @JoinColumn(name = "classroom_id"), inverseJoinColumns = @JoinColumn(name = "assignment_id"))
    private List<Assignment> assignments;

    public Classroom(String name, String subject, String grade, String teacher) {
        this.name = name;
        this.subject = subject;
        this.grade = grade;
        this.teacher = teacher;
    }

    public Classroom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public void addStudent(String student) {
        this.students.add(student);
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void addAssignment(Assignment assignment) {
        // for (String student : students) {
        // assignment.addStudentScore(student, -1);
        // }
    }

}
