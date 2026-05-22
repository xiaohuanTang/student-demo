# 学生考勤管理系统

基于 Spring Boot 开发的 Web 应用，用于学生信息管理和课堂考勤打卡。

## 主要功能

- **学生管理**：学生的增删改查
- **考勤打卡**：选择课程打卡，自动判断迟到
- **考勤记录**：按日期/状态筛选查看打卡历史

## 技术栈

Spring Boot + Thymeleaf + Spring JDBC + MySQL + Bootstrap

## 快速启动

1. 创建数据库 `student_demo`，执行项目中的 SQL 脚本
2. 修改 `application.properties` 中的数据库密码
3. 运行 `StudentDemoApplication.java`
4. 访问 http://localhost:8080/student/list

## 访问地址

| 页面 | 地址 |
|------|------|
| 学生列表 | /student/list |
| 考勤打卡 | /attendance/checkIn |
| 考勤记录 | /attendance/list |
