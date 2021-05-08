package com.eutopiagreen.webapp.student;

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
            Student tareq = new Student("Tareq Saifullah","tarqsaifu@gmail.com", LocalDate.of(1997, Month.JANUARY,5));
            Student jh = new Student("jm jhileek","jm@gmail.com", LocalDate.of(1997, Month.JANUARY,5));

            repository.saveAll(List.of(tareq,jh));


        };

    }
}
