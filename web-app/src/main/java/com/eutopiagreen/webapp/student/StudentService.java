package com.eutopiagreen.webapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
//        return List.of(
//                new Student(1L,"Tareq Saifullah","tarqsaifu@gmail.com", LocalDate.of(1997, Month.JANUARY,5),21)
//        );

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exits = studentRepository.existsById(studentId);
        if(!exits){
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exits");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id " + studentId + "does not exits "
                ));
        if(name != null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}