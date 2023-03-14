package com.example.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.school.model.*;

import com.example.school.service.StudentH2Service;

@RestController
public class StudentController{
    @Autowired
    private StudentH2Service studentservice;

    @GetMapping("/students")
    public ArrayList<Student> getSudents(){
        return studentservice.getStudents();
    }
    @GetMapping("/students/{studentId}")
    public Student getStudentbyId(@PathVariable("studentId") int studentId){
        return studentservice.getStudentbyId(studentId);
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentservice.addStudent(student);
    }
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId,@RequestBody Student student){
        return studentservice.updateStudent(studentId, student);
    }
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentservice.deleteStudent(studentId);
    }
    @PostMapping("/students/bulk")
    public String addBulkStudent(@RequestBody ArrayList<Student> arr){
        return studentservice.addBulkStudent(arr);
    }

    
}
