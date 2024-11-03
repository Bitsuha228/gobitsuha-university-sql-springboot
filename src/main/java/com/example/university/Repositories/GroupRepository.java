package com.example.university.Repositories;

import com.example.university.DataAccess.Group;
import com.example.university.ReportDTOs.GroupReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT new com.example.university.ViewDTOs.GroupReport(g.name, COUNT(s), g.faculty.id) " +
            "FROM Group g " +
            "JOIN Student s ON s.group = g " +
            "GROUP BY g.name, g.faculty.id")
    List<GroupReport> StudentCountByGroups();
    @Query("SELECT new com.example.university.ViewDTOs.GroupReport(g.name, COUNT(s), g.faculty.id) " +
            "FROM Group g " +
            "JOIN Student s ON s.group = g " +
            "WHERE g.faculty.id=?1 " +
            "GROUP BY g.name, g.faculty.id")
    List<GroupReport> StudentCountByGroupsOfFaculty(Long id);
    @Procedure(value = "clearAllByGroup")
    void deleteGroupById(Long id);
}
/*
* select u_group.name, count(student.name) as quantity, u_group.faculty_id as faculty_id
* from student right join u_group on student.u_group_id = u_group.id group by u_group.name;
*/