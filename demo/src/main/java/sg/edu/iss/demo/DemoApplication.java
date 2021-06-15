package sg.edu.iss.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.demo.domain.Department;
import sg.edu.iss.demo.domain.Employee;
import sg.edu.iss.demo.domain.ParkingSpace;
import sg.edu.iss.demo.domain.Project;
import sg.edu.iss.demo.repo.DepartmentRepository;
import sg.edu.iss.demo.repo.EmployeeRepository;
import sg.edu.iss.demo.repo.ParkingSpaceRepository;
import sg.edu.iss.demo.repo.ProjectRepository;
import sg.edu.iss.demo.repo.StudentRepository;

@SpringBootApplication

public class DemoApplication {
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	EmployeeRepository erepo;
	
	@Autowired
	ParkingSpaceRepository prepo;
	
	@Autowired
	ProjectRepository prjrepo;
	
	@Autowired
	DepartmentRepository drepo;
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			
			/*
			 * ParkingSpace ps = new ParkingSpace(1, "25 HMKT"); Employee emp = new
			 * Employee("Dilbert", 1000); emp.setParkingSpace(ps); erepo.save(emp);
			 * Department dept = new Department("Office Politics"); drepo.save(dept);
			 * Project p1 = new Project("Secret DSTA"); Project p2 = new
			 * Project("Trace Together"); prjrepo.save(p1);prjrepo.save(p2);
			 * emp.setDepartment(dept); List<Project> plist = new ArrayList<Project>();
			 * plist.add(p1); plist.add(p2); emp.setProjects(plist);
			 * erepo.saveAndFlush(emp);
			 */
			
			
			/*
			 * Student s1 = new Student("Hou Lu", "Sweet", null, 5000.00, 4.50); Student s2
			 * = new Student("Zerita", "Nice", null, 5000.00, 4.50); Student s3 = new
			 * Student("Austin", "Rationlist", null, 5000.00, 4.50); Student s4 = new
			 * Student("Benjo", "Smart", null, 5000.00, 5.00);
			 * srepo.save(s1);srepo.save(s2);srepo.save(s3);srepo.save(s4); //List All
			 * List<Student> list = srepo.findAll(); System.out.println("All Records"); for
			 * (Student student : list) {
			 * System.out.println("Record from DB"+student.toString()); } // CGPA and fee
			 * Finder List<Student> list1 = srepo.findStudentsByCgpaAndFee(5.0, 5000.00);
			 * System.out.println("Finder 1"); for (Student student : list1) {
			 * System.out.println("Record from DB"+student.toString()); } // Nick Name
			 * finder List<Student> list2 = srepo.queryStudentsByNickName("S%");
			 * System.out.println("Finder 2"); for (Student student : list2) {
			 * System.out.println("Record from DB"+student.toString()); } //Name finder
			 * List<Student> list3 = srepo.queryStudentsByName("Z%");
			 * System.out.println("Finder 3"); for (Student student : list3) {
			 * System.out.println("Record from DB"+student.toString()); } //Name finder
			 * List<Student> list4 = srepo.findStudentsByNickNameStartingWith("R");
			 * System.out.println("Finder 4"); for (Student student : list4) {
			 * System.out.println("Record from DB"+student.toString()); }
			 */
			     
		};
	}
}
