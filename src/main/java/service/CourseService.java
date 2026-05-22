package com.example.studentdemo.service;

import com.example.studentdemo.entity.Course;
import com.example.studentdemo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> findByStudentId(String studentId) {
        return courseMapper.findByStudentId(studentId);
    }

    public Course findById(Long id) {
        return courseMapper.findById(id);
    }

    public List<Course> findAll() {
        return courseMapper.findAll();
    }
}