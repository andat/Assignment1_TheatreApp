package com.example.Assignment2_LabApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;

@Entity
public class Submission {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    @JsonManagedReference
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonManagedReference
    private Student student;

    @Min(1) @Max(10)
    @Column(name = "grade")
    private int grade;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Student getStudent() {
        return student;
    }

    public int getGrade() {
        return grade;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}