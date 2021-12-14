package com.example.BuildinganAPI.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student joann = new Student(
                    "Joann",
                    "jojo@gmail.com",
                    LocalDate.of(1996, Month.OCTOBER,4)
            );
            Student josh = new Student(
                    "Josh",
                    "jcole@gmail.com",
                    LocalDate.of(1996, Month.AUGUST,20)
            );
            Student jakie = new Student(
                    "Jakie",
                    "chaka@gmail.com",
                    LocalDate.of(2001, Month.JANUARY,2)
            );

            repository.saveAll(
                    List.of(joann,josh,jakie)
            );
        };
    }
}
