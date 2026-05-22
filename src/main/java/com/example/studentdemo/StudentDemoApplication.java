package com.example.studentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentDemoApplication.class, args);
        System.out.println("========================================");
        System.out.println("  学生考勤管理系统启动成功！");
        System.out.println("  访问地址: http://localhost:8080");
        System.out.println("========================================");
    }
}