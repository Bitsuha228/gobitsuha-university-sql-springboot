package com.example.university.Repositories;

import com.example.university.DataAccess.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value = "select * from faculty_student_quantity", nativeQuery = true)
    List<String[]> StudentCountByFaculties();
    @Procedure(value = "clearAllByFaculty")
    void deleteFacultyById(Long id);
}
