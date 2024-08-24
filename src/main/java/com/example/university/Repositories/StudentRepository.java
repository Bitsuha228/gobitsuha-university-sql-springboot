package com.example.university.Repositories;

import com.example.university.DataAccess.Group;
import com.example.university.DataAccess.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.group = ?1")
    List<Student> findStudentByGroup(Group group);
}
