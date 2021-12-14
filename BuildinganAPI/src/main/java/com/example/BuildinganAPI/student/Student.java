package com.example.BuildinganAPI.student;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

@Entity //This is for Hibernate
@Table // For the table in our database
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient //Says this does not need to be a column in the database
    private Integer age;

    public Student() { // 1st constructor with no requirements
    }

    public Student(long id, String name, String email, LocalDate dob) { //constructor with all variables
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) { //constructor with all but id variable
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    // the getter section, allowing you to get just one variable from a student

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    // setter section, allowing you to set the variable for a specific student

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {this.age = age;}

    // toString() allows for proper printing of the student being requested

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}


