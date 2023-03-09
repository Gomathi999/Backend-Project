package com.example.smsProject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smsProject.model.Student;

//This Interface extends JpaRepository interface, so that @Repository is not needed to be annotated manually
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
