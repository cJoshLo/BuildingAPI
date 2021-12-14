package com.example.BuildinganAPI.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> { // <Student, Long> type of repository that it will work with, and the second is the id type

    //SELECT * FROM student WHERE email = ?
    Optional<Student> findStudentByEmail(String email);

}
