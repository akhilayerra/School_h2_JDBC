package com.example.school.repository;
import com.example.school.model.*;
import java.util.*;

public interface StudentRepository{
    ArrayList<Student> getStudents();
    Student getStudentbyId(int studentId);
    Student addStudent(Student student);
    Student updateStudent(int studentId,Student student);
    void deleteStudent(int studentId);
    String addBulkStudent(ArrayList<Student> arr);
}