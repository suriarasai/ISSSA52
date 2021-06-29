package sg.edu.iss.cats.services;

import java.util.ArrayList;

import sg.edu.iss.cats.model.CourseEvent;

public interface CourseEventService {

	ArrayList<CourseEvent> findAllCourseEvents();

	CourseEvent findCourseEvent(Integer ceid);

	CourseEvent createCourseEvent(CourseEvent courseEvent);

	CourseEvent changeCourseEvent(CourseEvent courseEvent);

	void removeCourseEvent(CourseEvent courseEvent);

}