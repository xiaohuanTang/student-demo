package com.example.studentdemo.entity;

import java.time.LocalTime;

public class Course {
    private Long id;
    private String courseName;
    private String courseCode;
    private String className;
    private LocalTime startTime;

    public Course() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
}