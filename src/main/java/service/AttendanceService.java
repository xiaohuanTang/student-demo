package com.example.studentdemo.service;

import com.example.studentdemo.entity.Attendance;
import com.example.studentdemo.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    public void save(Attendance attendance) {
        attendanceMapper.save(attendance);
    }

    public List<Attendance> findByStudentId(String studentId) {
        return attendanceMapper.findByStudentId(studentId);
    }

    public List<Attendance> findByCondition(String studentId, String startDate, String endDate, String status) {
        return attendanceMapper.findByCondition(studentId, startDate, endDate, status);
    }
}