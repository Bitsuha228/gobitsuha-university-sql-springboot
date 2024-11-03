package com.example.university.Repositories;

import com.example.university.DataAccess.Faculty;
import com.example.university.ReportDTOs.FacultyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query("SELECT new com.example.university.ViewDTOs.FacultyReport(f.name, COUNT(s), f.id) " +
            "FROM Faculty f " +
            "JOIN Group g ON g.faculty = f " +
            "JOIN Student s ON s.group = g " +
            "GROUP BY f.name, f.id")
    List<FacultyReport> StudentCountByFaculties();

    @Procedure(value = "clearAllByFaculty")
    void deleteFacultyById(Long id);
}
