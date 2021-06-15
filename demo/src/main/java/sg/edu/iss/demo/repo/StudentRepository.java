package sg.edu.iss.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.demo.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	// CRUD - FindByID FindByOne FindAll save() delete()
	// Standard Queries
	// findStudentsByCGPAAndFee
	List<Student> findStudentsByCgpaAndFee(double cgpa, double fee);
	// findStudentsByNickName
	List<Student> findStudentsByNickNameStartingWith(String phrase);
	
	//Customized Query using Annotations
	@Query("Select s from Student s where s.nickName like %?1")
	List<Student> queryStudentsByNickName(String nn);
	//Customized Query using Annotations
	@Query("Select s from Student s where s.name like :name")
	List<Student> queryStudentsByName(@Param("name") String name);
	
}
