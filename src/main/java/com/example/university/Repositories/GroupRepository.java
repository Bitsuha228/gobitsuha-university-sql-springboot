package com.example.university.Repositories;

import com.example.university.DataAccess.Faculty;
import com.example.university.DataAccess.Group;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value = "select * from group_student_quantity", nativeQuery = true)
    List<String[]> StudentCountByGroups();
    @Procedure(value = "clearAllByGroup")
    void deleteGroupById(Long id);
}
