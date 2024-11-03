package com.example.university.ReportDTOs;

public class GroupReport {
    private final String name;
    private final long quantity;
    private final Long faculty_id;

    public GroupReport(String name, long quantity, Long faculty_id) {
        this.name = name;
        this.quantity = quantity;
        this.faculty_id = faculty_id;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getFaculty_id() {
        return faculty_id;
    }

    @Override
    public String toString() {
        return "GroupReport{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
