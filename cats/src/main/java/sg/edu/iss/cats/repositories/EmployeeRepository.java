package sg.edu.iss.cats.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.cats.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	@Query("SELECT e FROM Employee e where e.employeeId = :id")
	Employee findEmployeeById(@Param("id") String id);
	
	@Query("SELECT e FROM Employee e where e.managerId = :mgrid")
	ArrayList<Employee> findEmployeesByManagerId(@Param("mgrid") String mgrid);
	
	@Query("SELECT DISTINCT m FROM Employee e, Employee m where e.managerId = m.employeeId ")
	ArrayList<Employee> findAllManagers();

	@Query("SELECT DISTINCT m.name FROM Employee e, Employee m where e.managerId = m.employeeId ")
	ArrayList<String> findAllManagerNames();
    
	@Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.employeeId = e2.managerId AND e1.employeeId = :eid")
	ArrayList<Employee> findSubordinates(@Param("eid") String eid);
	
	@Query("SELECT DISTINCT e.employeeId FROM Employee e")
	ArrayList<String> findAllEmployeeIDs();
}
