package sg.edu.iss.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.demo.domain.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("Select distinct d from Department as d join Employee as e where e.name=:n and d.id=e.department.id")
	public Department findDepartmentEmployeeWorksFor(@Param("n") String name);
	
	
}
