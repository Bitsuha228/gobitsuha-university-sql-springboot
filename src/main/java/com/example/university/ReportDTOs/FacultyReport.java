package com.example.university.ReportDTOs;


public class FacultyReport {
    private final String name;
    private final Long quantity;
    private final Long id;

    public FacultyReport(String name, long quantity, Long id) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FacultyReport{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", id=" + id +
                '}';
    }
}

