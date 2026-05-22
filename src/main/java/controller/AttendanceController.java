package com.example.studentdemo.controller;

import com.example.studentdemo.entity.Attendance;
import com.example.studentdemo.entity.Course;
import com.example.studentdemo.entity.Student;
import com.example.studentdemo.service.AttendanceService;
import com.example.studentdemo.service.CourseService;
import com.example.studentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/checkIn")
    public String checkInPage(Model model) {
        String studentId = getCurrentStudentId();
        List<Course> courses = courseService.findByStudentId(studentId);
        model.addAttribute("courses", courses);
        return "attendance-check-in";
    }

    @PostMapping("/checkIn")
    public String checkIn(@RequestParam Long courseId,
                          @RequestParam(required = false) String remark,
                          HttpServletRequest request) {
        String studentId = getCurrentStudentId();
        Student student = studentService.findByStudentNo(studentId);
        Course course = courseService.findById(courseId);

        if (student == null || course == null) {
            return "redirect:/attendance/checkIn?error=notFound";
        }

        LocalDateTime now = LocalDateTime.now();
        LocalTime classStartTime = course.getStartTime();

        LocalTime earliest = classStartTime.minusMinutes(15);
        LocalTime latest = classStartTime.plusMinutes(30);
        LocalTime currentTime = now.toLocalTime();

        if (currentTime.isBefore(earliest) || currentTime.isAfter(latest)) {
            return "redirect:/attendance/checkIn?error=notInTime";
        }

        String status = currentTime.isAfter(classStartTime) ? "LATE" : "NORMAL";

        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setStudentName(student.getName());
        attendance.setCourseId(courseId);
        attendance.setCourseName(course.getCourseName());
        attendance.setCheckInTime(now);
        attendance.setStatus(status);
        attendance.setRemark(remark);
        attendance.setIp(getClientIp(request));
        attendance.setCreateTime(now);

        attendanceService.save(attendance);
        return "redirect:/attendance/list?success=checkedIn";
    }

    @GetMapping("/list")
    public String list(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String status,
            Model model) {

        String studentId = getCurrentStudentId();
        List<Attendance> records = attendanceService.findByCondition(studentId, startDate, endDate, status);

        model.addAttribute("records", records);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("status", status);

        return "attendance-list";
    }

    private String getCurrentStudentId() {
        return "20240001";
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}