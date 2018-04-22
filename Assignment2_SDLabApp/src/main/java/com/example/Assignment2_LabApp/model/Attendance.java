package com.example.Assignment2_LabApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="lab_id")
    @JsonManagedReference
    private Laboratory laboratory;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonManagedReference
    private Student student;

    @Column(name = "present")
    private boolean present;

    public int getId() {
        return id;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isPresent() {
        return present;
    }

    public void setId(int id) {
        this.id = id;
    }
}