package com.example.university.Controller;

import com.example.university.DataAccess.Faculty;
import com.example.university.DataAccess.Group;
import com.example.university.DataAccess.Student;
import com.example.university.Repositories.FacultyRepository;
import com.example.university.Repositories.GroupRepository;
import com.example.university.Repositories.StudentRepository;
import com.example.university.Services.FacultyGroupReportService;
import com.example.university.ReportDTOs.FacultyGroupReport;
import com.example.university.ReportDTOs.FacultyReport;
import com.example.university.ReportDTOs.GroupReport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UniversityRESTController {
    private final FacultyRepository facultyRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final FacultyGroupReportService facultyGroupReportService;

    public UniversityRESTController(FacultyGroupReportService facultyGroupReportService, FacultyRepository facultyRepository, GroupRepository groupRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.facultyGroupReportService = facultyGroupReportService;
    }

    @GetMapping("/faculties")
    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }
    @GetMapping("/groups")
    public List<Group> getGroups(){
        return groupRepository.findAll();
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/faculties/{id}")
    public Faculty getFaculty(@PathVariable(value = "id") long id){
        return facultyRepository.findById(id).get();
    }
    @GetMapping("/groups/{id}")
    public Group getGroup(@PathVariable(value = "id") long id){
        return groupRepository.findById(id).get();
    }
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable(value = "id") long id){
        return studentRepository.findById(id).get();
    }
    @GetMapping("/reports/student-quantity-by-faculty")
    public List<FacultyReport> getStudentQuantityByFaculty(){
        return facultyRepository.StudentCountByFaculties();
    }
    @GetMapping("/reports/student-quantity-by-group")
    public List<GroupReport> getStudentQuantityByGroup(){
        return groupRepository.StudentCountByGroups();
    }
    @GetMapping("/reports/student-quantity-by-group-and-faculty")
    public List<FacultyGroupReport> getStudentQuantityByGroupOfFaculty(){
        return facultyGroupReportService.getFacultyGroupViews();
    }
    @PostMapping("/faculties/new")
    public Faculty newFaculty(@RequestBody Faculty faculty){
        return facultyRepository.save(faculty);
    }
    @PostMapping("/groups/new")
    public Group newGroup(@RequestBody Group group){
        return groupRepository.save(group);
    }
    @PostMapping("/students/new")
    public Student newStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
    @PutMapping("/faculties/{id}")
    Faculty replaceFaculty(@RequestBody Faculty newFaculty, @PathVariable Long id) {
        return facultyRepository.findById(id)
                .map(faculty -> {
                    faculty.setName(newFaculty.getName());
                    return facultyRepository.save(faculty);
                })
                .orElseGet(() -> {
                    return facultyRepository.save(newFaculty);
                });
    }
    @PutMapping("/groups/{id}")
    Group replaceGroup(@RequestBody Group newGroup, @PathVariable Long id) {
        return groupRepository.findById(id)
                .map(group -> {
                    group.setName(newGroup.getName());
                    group.setFaculty(newGroup.getFaculty());
                    return groupRepository.save(group);
                })
                .orElseGet(() -> {
                    return groupRepository.save(newGroup);
                });
    }
    @PutMapping("/students/{id}")
    Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setGroup(newStudent.getGroup());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    return studentRepository.save(newStudent);
                });
    }
    @DeleteMapping("/faculties/{id}")
    public void deleteFaculty(@PathVariable(value = "id") long id){
        facultyRepository.deleteFacultyById(id);
    }
    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable(value = "id") long id){
        groupRepository.deleteGroupById(id);
    }
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable(value = "id") long id){
        studentRepository.deleteById(id);
    }
}
