package sg.edu.iss.demo.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.demo.DemoApplication;
import sg.edu.iss.demo.domain.Student;

// To connect Spring fw
@ExtendWith(SpringExtension.class)
//To connect to project
@SpringBootTest(classes = DemoApplication.class )
// To create ordering amoung test methods
@TestMethodOrder(OrderAnnotation.class)
//To connect to configuration (application.properties)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryUnitTests {

	@Autowired
	StudentRepository srepo;
	
	@Test
	@Order(1)
	public void testCreateStudent() {
		// given
		Student s1 = new Student("Mok Hon Chuen", "TechyGeek", null, 5000.0, 5.0);
		//when
		Student saved = srepo.save(s1);
		//then
		assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void testDeleteStudent() {
		// given
		String nn = "T";
		Student s2 = srepo.findStudentsByNickNameStartingWith(nn).get(0);
		//when
		srepo.delete(s2);
		//then
		assertTrue(srepo.findStudentsByNickNameStartingWith(nn).size()==0);		
	}
	
}
