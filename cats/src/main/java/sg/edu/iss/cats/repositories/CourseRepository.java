package sg.edu.iss.cats.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.cats.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query("SELECT c from Course c WHERE c.employeeId = :eid")
	ArrayList<Course> findCoursesByEID(@Param("eid") String eid);
	
	@Query("SELECT c from Course c WHERE c.employeeId = :eid AND (c.status ='SUBMITTED' OR c.status ='UPDATED')")
	ArrayList<Course> findPendingCoursesByEID(@Param("eid") String eid);
	
	@Query(value = "SELECT * FROM course WHERE status = ?0", nativeQuery = true)
	ArrayList<Course> findPendingCoursesByStatus(String status);
	
	

}
