package sg.edu.iss.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
			Student s4 = new Student("Benjo", "ExamSmart", null, 5000.00, 5.00);
			srepo.save(s1);srepo.save(s2);srepo.save(s3);srepo.save(s4);
			List<Student> list = srepo.findAll();
			for (Student student : list) {
				System.out.println("Record from DB"+student.toString());
			}
			
			     
		};
	}
}
