package sg.edu.iss.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import sg.edu.iss.demo.domain.Student;
import sg.edu.iss.demo.repo.StudentRepository;

@SpringBootApplication

public class DemoApplication {
	
	@Autowired
	StudentRepository srepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			Student s1 = new Student("Hou Lu", "Sweet", null, 5000.00, 4.50);
			Student s2 = new Student("Zerita", "Nice", null, 5000.00, 4.50);
			Student s3 = new Student("Austin", "Rationlist", null, 5000.00, 4.50);
			Student s4 = new Student("Benjo", "Smart", null, 5000.00, 5.00);
			srepo.save(s1);srepo.save(s2);srepo.save(s3);srepo.save(s4);
			//List All
			List<Student> list = srepo.findAll();
			System.out.println("All Records");
			for (Student student : list) {
				System.out.println("Record from DB"+student.toString());
			}
			// CGPA and fee Finder
			List<Student> list1 = srepo.findStudentsByCgpaAndFee(5.0, 5000.00);
			System.out.println("Finder 1");
			for (Student student : list1) {
				System.out.println("Record from DB"+student.toString());
			}
			// Nick Name finder
			List<Student> list2 = srepo.queryStudentsByNickName("S%");
			System.out.println("Finder 2");
			for (Student student : list2) {
				System.out.println("Record from DB"+student.toString());
			}
			//Name finder
			List<Student> list3 = srepo.queryStudentsByName("Z%");
			System.out.println("Finder 3");
			for (Student student : list3) {
				System.out.println("Record from DB"+student.toString());
			}
			//Name finder
			List<Student> list4 = srepo.findStudentsByNickNameStartingWith("R");
			System.out.println("Finder 4");
			for (Student student : list4) {
				System.out.println("Record from DB"+student.toString());
			}
			     
		};
	}
}
