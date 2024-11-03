package com.example.university.ReportDTOs;

import java.util.List;

public class FacultyGroupReport {
    private final String name;
    private final Long quantity;
    private final List<GroupReport> groupReports;
    private final Long id;

    public FacultyGroupReport(FacultyReport facultyReport, List<GroupReport> groupReports) {
        this.name = facultyReport.getName();
        this.quantity = facultyReport.getQuantity();
        this.groupReports = groupReports;
        this.id = facultyReport.getId();
    }

    public String getName() {
        return name;
    }

    public List<GroupReport> getGroupViews() {
        return groupReports;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FacultyGroupReport{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", groupReports=" + groupReports +
                '}';
    }
}
