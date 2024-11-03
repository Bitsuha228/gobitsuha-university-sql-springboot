package com.example.university.Services;

import com.example.university.Repositories.FacultyRepository;
import com.example.university.Repositories.GroupRepository;
import com.example.university.ReportDTOs.FacultyGroupReport;
import com.example.university.ReportDTOs.FacultyReport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyGroupReportService {
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    public FacultyGroupReportService(GroupRepository groupRepository, FacultyRepository facultyRepository) {
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
    }
    public List<FacultyGroupReport> getFacultyGroupViews(){
        List<FacultyGroupReport> list = new ArrayList<>();
        for (FacultyReport facultyReport : facultyRepository.StudentCountByFaculties()) {
            list.add(new FacultyGroupReport(facultyReport, groupRepository.StudentCountByGroupsOfFaculty(facultyReport.getId())));
        }
        return list;
    }
}
