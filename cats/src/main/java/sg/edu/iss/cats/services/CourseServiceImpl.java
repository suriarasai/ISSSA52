package sg.edu.iss.cats.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.cats.model.Course;
import sg.edu.iss.cats.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Resource
	private CourseRepository courseRepository;
	

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#findAllCourses()
	 */
	@Override
	@Transactional
	public ArrayList<Course> findAllCourses() {
		ArrayList<Course> l = (ArrayList<Course>) courseRepository.findAll();
		return l;
	}


	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#findCourse(java.lang.String)
	 */
	@Override
	@Transactional
	public Course findCourse(Integer ceid) {
		return courseRepository.findById(ceid).orElse(null);

	}

	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#createCourse(edu.iss.cats.model.Course)
	 */
	@Override
	@Transactional
	public Course createCourse(Course course) {
		return courseRepository.saveAndFlush(course);
	}

	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#changeCourse(edu.iss.cats.model.Course)
	 */
	@Override
	@Transactional
	public Course changeCourse(Course course) {
		return courseRepository.saveAndFlush(course);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#removeCourse(edu.iss.cats.model.Course)
	 */
	@Override
	@Transactional
	public void removeCourse(Course course) {
		courseRepository.delete(course);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#findCoursesByEID(java.lang.String)
	 */
	@Override
	@Transactional
	public ArrayList<Course> findCoursesByEID(String eid) {
		return courseRepository.findCoursesByEID(eid);
	}
	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.CourseService#findPendingCoursesByEID(java.lang.String)
	 */
	@Override
	@Transactional
	public ArrayList<Course> findPendingCoursesByEID(String eid) {
		return courseRepository.findPendingCoursesByEID(eid);
	}
}
