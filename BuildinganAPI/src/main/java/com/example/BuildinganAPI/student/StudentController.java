package com.example.BuildinganAPI.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController//makes class sever REST endpoints
@RequestMapping(path = "api/v1/student") //changes the URL form localhost:8080 to localhost:8080/api/v1/students
public class StudentController {

    private final StudentService studentService; // a reference to student service

    @Autowired //StudentService will be injected into this constructor for us
    public StudentController(StudentService studentService) { //pass it inside the controller
        this.studentService = studentService;
    }

    @GetMapping//makes this a RESTful call
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){ //Take the request body and map it into a Student
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
            ){
        studentService.updateStudent(studentId, name , email);
    }


}
