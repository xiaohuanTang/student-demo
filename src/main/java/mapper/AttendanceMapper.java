package com.example.studentdemo.mapper;

import com.example.studentdemo.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AttendanceMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Attendance attendance) {
        String sql = "INSERT INTO attendance(student_id, student_name, course_id, course_name, check_in_time, status, remark, ip, create_time) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                attendance.getStudentId(),
                attendance.getStudentName(),
                attendance.getCourseId(),
                attendance.getCourseName(),
                attendance.getCheckInTime(),
                attendance.getStatus(),
                attendance.getRemark(),
                attendance.getIp(),
                attendance.getCreateTime());
    }

    public List<Attendance> findByStudentId(String studentId) {
        String sql = "SELECT * FROM attendance WHERE student_id = ? ORDER BY check_in_time DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Attendance.class), studentId);
    }

    public List<Attendance> findByCondition(String studentId, String startDate, String endDate, String status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM attendance WHERE student_id = ?");
        if (startDate != null && !startDate.isEmpty()) {
            sql.append(" AND DATE(check_in_time) >= '").append(startDate).append("'");
        }
        if (endDate != null && !endDate.isEmpty()) {
            sql.append(" AND DATE(check_in_time) <= '").append(endDate).append("'");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = '").append(status).append("'");
        }
        sql.append(" ORDER BY check_in_time DESC");
        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Attendance.class), studentId);
    }
}