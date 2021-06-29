package sg.edu.iss.cats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.cats.model.CourseEvent;

public interface CourseEventRepository extends JpaRepository<CourseEvent, Integer>{

}
