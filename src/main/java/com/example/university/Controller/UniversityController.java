package com.example.university.Controller;

import com.example.university.DataAccess.Faculty;
import com.example.university.DataAccess.Group;
import com.example.university.DataAccess.Student;
import com.example.university.Repositories.FacultyRepository;
import com.example.university.Repositories.GroupRepository;
import com.example.university.Repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class UniversityController {
    private final FacultyRepository facultyRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public UniversityController(FacultyRepository facultyRepository, GroupRepository groupRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }
    @GetMapping("/")
    public String Redirect(){
        return "redirect:/faculties";
    }
    @GetMapping("/faculties")
    public String getFaculties(Model model){
        model.addAttribute("faculties", facultyRepository.findAll());
        return "faculties";
    }
    @GetMapping("/groups")
    public String getGroups(Model model){
        model.addAttribute("groups", groupRepository.findAll());
        return "groups";
    }
    @GetMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }
    @GetMapping("/faculties/new")
    public String addNewEntry(Model model) {
        Faculty faculty = new Faculty();
        model.addAttribute("faculty", faculty);
        return "newfaculty";
    }
    @GetMapping("/reports")
    public String report1(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        model.addAttribute("groups", groupRepository.findAll());
        return "report";
    }
    @GetMapping("/reports/student-quantity-by-group")
    public String report2(Model model){
        model.addAttribute("list", groupRepository.StudentCountByGroups());
        return "report2";
    }
    @GetMapping("/reports/student-quantity-by-faculty")
    public String repost3(Model model){
        model.addAttribute("list", facultyRepository.StudentCountByFaculties());
        return "report3";
    }
    @GetMapping("/reports/students-by-group")
    public String report1(@ModelAttribute("group") Group group, Model model) {
        try {
            model.addAttribute("students", studentRepository.findStudentByGroup(group));
            return "report1";
        }
        catch (Exception e){
            return "redirect:/reports";
        }
    }

    @PostMapping("/faculties/new")
    public String saveEntry(@ModelAttribute("faculty") Faculty faculty) {
        facultyRepository.save(faculty);
        return "redirect:/faculties";
    }
    @PutMapping("/faculties/{id}")
    public String updateEntry(@PathVariable(value = "id") Long id, @ModelAttribute("faculty") Faculty faculty) {
        facultyRepository.save(faculty);
        return "redirect:/faculties";
    }

    @GetMapping("/students/new")
    public String addNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("groups", groupRepository.findAll());
        return "newstudent";
    }

    @PostMapping("/students/new")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }
    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable(value = "id") Long id, @ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/groups/new")
    public String addNewGroup(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        model.addAttribute("faculties", facultyRepository.findAll());
        return "newgroup";
    }

    @PostMapping("/groups/new")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupRepository.save(group);
        return "redirect:/groups";
    }
    @PutMapping("/groups/{id}")
    public String updateGroup(@PathVariable(value = "id") Long id, @ModelAttribute("group") Group group) {
        groupRepository.save(group);
        return "redirect:/groups";
    }
    @GetMapping("/faculties/{id}/confirm-delete")
    public String deleteFaculty(@PathVariable(value = "id") long id, Model model) {
        if(facultyRepository.findById(id).isPresent()) {
            Faculty faculty = facultyRepository.findById(id).get();
            model.addAttribute("faculty", faculty);
            return "deletefaculty";
        }
        return "redirect:/";
    }
    @GetMapping("/groups/{id}/confirm-delete")
    public String deleteGroup(@PathVariable(value = "id") long id, Model model) {
        if(groupRepository.findById(id).isPresent()) {
            Group group = groupRepository.findById(id).get();
            model.addAttribute("group", group);
            return "deletegroup";
        }
        return "redirect:/groups";
    }
    @GetMapping("/students/{id}/confirm-delete")
    public String deleteStudent(@PathVariable(value = "id") long id, Model model) {
        if(studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            model.addAttribute("student", student);
            return "deletestudent";
        }
        return "redirect:/students";
    }
    @DeleteMapping("/faculties/{id}")
    public String deleteFaculty(@PathVariable(value = "id") long id) {
        facultyRepository.deleteFacultyById(id);
        return "redirect:/";
    }
    @DeleteMapping("/groups/{id}")
    public String deleteGroup(@PathVariable(value = "id") long id) {
        groupRepository.deleteGroupById(id);
        return "redirect:/groups";
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
    @GetMapping("/faculties/{id}")
    public String updateFaculty(@PathVariable(value = "id") long id, Model model){
        if(facultyRepository.existsById(id)){
            Faculty faculty = facultyRepository.findById(id).get();
            model.addAttribute("faculty", faculty);
            return "updatefaculty";
        }
        return "redirect:/";
    }
    @GetMapping("/groups/{id}")
    public String updateGroup(@PathVariable(value = "id") long id, Model model){
        if(groupRepository.existsById(id)){
            Group group = groupRepository.findById(id).get();
            model.addAttribute("group", group);
            model.addAttribute("faculties", facultyRepository.findAll());
            return "updategroup";
        }
        return "redirect:/groups";
    }
    @GetMapping("/students/{id}")
    public String updateStudent(@PathVariable(value = "id") long id, Model model){
        if(studentRepository.existsById(id)){
            Student student = studentRepository.findById(id).get();
            model.addAttribute("student", student);
            model.addAttribute("groups", groupRepository.findAll());
            return "updatestudent";
        }
        return "redirect:/students";
    }
}
