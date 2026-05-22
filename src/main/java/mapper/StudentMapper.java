package com.example.studentdemo.mapper;

import com.example.studentdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student findById(Long id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
    }

    public Student findByStudentNo(String studentNo) {
        String sql = "SELECT * FROM student WHERE student_no = ?";
        List<Student> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), studentNo);
        return list.isEmpty() ? null : list.get(0);
    }

    public int save(Student student) {
        String sql = "INSERT INTO student(name, student_no, class_name) VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, student.getName(), student.getStudentNo(), student.getClassName());
    }

    public int update(Student student) {
        String sql = "UPDATE student SET name = ?, student_no = ?, class_name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getStudentNo(), student.getClassName(), student.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}