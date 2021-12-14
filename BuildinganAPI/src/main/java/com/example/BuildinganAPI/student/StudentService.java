package com.example.BuildinganAPI.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //Links to the constructor, same this as @constructor but @service is more specific in Spring
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();// This returns a list
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email is already in use");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional // this allows us to update the DB without using SQL
    public void updateStudent(Long studentId, String name, String email){

        // start with checking of the student exists with the id provided
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "studnet with id " + studentId + " does not exist"
        ));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null
                && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) { // checking if the name provided is not the name already in use
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email); //checking to see if the email is already in use
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email is already in use");
            }
            student.setEmail(email);
        }

    }

}
