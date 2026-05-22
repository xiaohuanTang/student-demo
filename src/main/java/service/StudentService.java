package com.example.studentdemo.service;

import com.example.studentdemo.entity.Student;
import com.example.studentdemo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    public Student findById(Long id) {
        return studentMapper.findById(id);
    }

    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    public void save(Student student) {
        if (student.getId() == null) {
            studentMapper.save(student);
        } else {
            studentMapper.update(student);
        }
    }

    public void deleteById(Long id) {
        studentMapper.deleteById(id);
    }
}