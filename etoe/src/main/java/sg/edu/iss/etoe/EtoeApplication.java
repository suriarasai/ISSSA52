package sg.edu.iss.etoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.etoe.model.RoleType;
import sg.edu.iss.etoe.model.User;
import sg.edu.iss.etoe.repo.UserRepository;

@SpringBootApplication
public class EtoeApplication {
	
	@Autowired
	UserRepository urepo;

	public static void main(String[] args) {
		SpringApplication.run(EtoeApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			//User u = new User("dilbert", "dilbert", RoleType.STUDENT);
			//urepo.save(u);
			
		};
	}
}
