package com.example.university.Controller;

import com.example.university.DataAccess.Faculty;
import com.example.university.DataAccess.Group;
import com.example.university.DataAccess.Student;
import com.example.university.Repositories.FacultyRepository;
import com.example.university.Repositories.GroupRepository;
import com.example.university.Repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/addFaculty")
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
    @GetMapping("/reports2")
    public String report2(Model model){
        model.addAttribute("list", groupRepository.StudentCountByGroups());
        return "report2";
    }
    @GetMapping("/reports3")
    public String repost3(Model model){
        model.addAttribute("list", facultyRepository.StudentCountByFaculties());
        return "report3";
    }
    @GetMapping("/reports1")
    public String report1(@ModelAttribute("group") Group group, Model model) {
        try {
            model.addAttribute("students", studentRepository.findStudentByGroup(group));
            return "report1";
        }
        catch (Exception e){
            return "redirect:/reports";
        }
    }

    @PostMapping("/saveFaculty")
    public String saveEntry(@ModelAttribute("faculty") Faculty faculty) {
        facultyRepository.save(faculty);
        return "redirect:/";
    }

    @GetMapping("/addStudent")
    public String addNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("groups", groupRepository.findAll());
        return "newstudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/addGroup")
    public String addNewGroup(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        model.addAttribute("faculties", facultyRepository.findAll());
        return "newgroup";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupRepository.save(group);
        return "redirect:/groups";
    }
    @GetMapping("/deletefaculty/{id}")
    public String deleteFaculty(@PathVariable(value = "id") long id, Model model) {
        if(facultyRepository.findById(id).isPresent()) {
            Faculty faculty = facultyRepository.findById(id).get();
            model.addAttribute("faculty", faculty);
            return "deletefaculty";
        }
        return "redirect:/";
    }
    @GetMapping("/deletegroup/{id}")
    public String deleteGroup(@PathVariable(value = "id") long id, Model model) {
        if(groupRepository.findById(id).isPresent()) {
            Group group = groupRepository.findById(id).get();
            model.addAttribute("group", group);
            return "deletegroup";
        }
        return "redirect:/groups";
    }
    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id, Model model) {
        if(studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            model.addAttribute("student", student);
            return "deletestudent";
        }
        return "redirect:/students";
    }
    @PostMapping("/cdfaculty")
    public String deleteFaculty(@ModelAttribute("faculty") Faculty faculty) {
        facultyRepository.deleteFacultyById(faculty.getId());
        return "redirect:/";
    }
    @PostMapping("/cdgroup")
    public String deleteGroup(@ModelAttribute("group") Group group) {
        groupRepository.deleteGroupById(group.getId());
        return "redirect:/groups";
    }
    @PostMapping("/cdstudent")
    public String deleteGroup(@ModelAttribute("student") Student student) {
        studentRepository.delete(student);
        return "redirect:/students";
    }
    @GetMapping("/faculties/update/{id}")
    public String updateFaculty(@PathVariable(value = "id") long id, Model model){
        if(facultyRepository.existsById(id)){
            Faculty faculty = facultyRepository.findById(id).get();
            model.addAttribute("faculty", faculty);
            return "newfaculty";
        }
        return "redirect:/";
    }
    @GetMapping("/groups/update/{id}")
    public String updateGroup(@PathVariable(value = "id") long id, Model model){
        if(groupRepository.existsById(id)){
            Group group = groupRepository.findById(id).get();
            model.addAttribute("group", group);
            model.addAttribute("faculties", facultyRepository.findAll());
            return "newgroup";
        }
        return "redirect:/groups";
    }
    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable(value = "id") long id, Model model){
        if(studentRepository.existsById(id)){
            Student student = studentRepository.findById(id).get();
            model.addAttribute("student", student);
            model.addAttribute("groups", groupRepository.findAll());
            return "newstudent";
        }
        return "redirect:/students";
    }
}
