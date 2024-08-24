package com.example.university;

import com.example.university.DataAccess.Faculty;
import com.example.university.DataAccess.Group;
import com.example.university.DataAccess.Student;
import com.example.university.Repositories.FacultyRepository;
import com.example.university.Repositories.GroupRepository;
import com.example.university.Repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}
	/*
    @Bean
	CommandLineRunner commandLineRunner(FacultyRepository facultyRepository, GroupRepository groupRepository){
		return (args) -> {
			Faculty faculty = new Faculty();
			Group group = new Group();
			faculty.setName("IKTA");
			group.setName("ki-205");
			group.setFaculty(faculty);
			facultyRepository.save(faculty);
			groupRepository.save(group);
		};
	}
	@Bean
	CommandLineRunner commandLineRunner(GroupRepository groupRepository){
		return (args) -> {
		List<String[]> list = groupRepository.StudentCountByGroups();
            for (String[] strings : list) {
                System.out.println(strings[0] + " " + strings[1]);
            }
		};
	}
	@Bean
	CommandLineRunner commandLineRunner(FacultyRepository facultyRepository){
		return (args) -> {
			List<String[]> list = facultyRepository.StudentCountByFaculties();
			for (String[] strings : list) {
				System.out.println(strings[0] + " " + strings[1]);
			}
		};
	}*/
}
