package com.example.school.service;
import com.example.school.repository.*;
import com.example.school.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;


@Service
public class StudentH2Service implements StudentRepository{
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents(){
        List<Student> student1=db.query("select * from STUDENT",new StudentRowMapper());
        ArrayList<Student> students=new ArrayList<>(student1);
        return students;
    }
    @Override
    public Student getStudentbyId(int studentId){
        try{
        Student student=db.queryForObject("select * from STUDENT where studentId=?",new StudentRowMapper(),studentId);
        return student;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Student addStudent(Student student){
        db.update("insert into STUDENT(studentName,gender,standard) values(?,?,?)",student.getStudentName(),student.getGender(),student.getStandard());
        Student savedStudent=db.queryForObject("select * from STUDENT where studentName=? and gender=? and standard=?",new StudentRowMapper(),student.getStudentName(),student.getGender(),student.getStandard());
        return savedStudent;
    }

    @Override
    public Student updateStudent(int studentId,Student student){
        try{
        Integer r=Integer.valueOf(student.getStandard());
        if(student.getStudentName()!=null){
            db.update("update STUDENT set studentName=? where studentId=?",student.getStudentName(),studentId);
        }
        if(student.getGender()!=null){
            db.update("update STUDENT set gender=? where studentId=?",student.getGender(),studentId);
        }
        if(r!=null){
            db.update("update STUDENT set standard=? where studentId=?",student.getStandard(),studentId);
        }
        return getStudentbyId(studentId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void deleteStudent(int studentId){
        try{
            db.update("delete from STUDENT where studentId=?",studentId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.OK);
        }
    }
    int ads=0;
    @Override
    public String addBulkStudent(ArrayList<Student> arr){
        for(Student student:arr){
            db.update("insert into STUDENT(studentName,gender,standard) values(?,?,?)",student.getStudentName(),student.getGender(),student.getStandard());
            ads=ads+1;
        }
        String adss=String.valueOf(ads);
        String re="Successfully added "+adss+" students";
        return re;
    }
}