package sg.edu.iss.cats.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.cats.exception.CourseNotFound;
import sg.edu.iss.cats.helper.CourseEventEnum;
import sg.edu.iss.cats.model.Course;
import sg.edu.iss.cats.model.CourseEvent;
import sg.edu.iss.cats.services.CourseEventService;
import sg.edu.iss.cats.services.CourseService;
import sg.edu.iss.cats.validators.CourseValidator;

@Controller
@RequestMapping(value = "/staff")
public class StaffController {
	@Autowired
	private CourseService cService;

	@Autowired
	private CourseValidator cValidator;

	@Autowired
	private CourseEventService ceService;

	@InitBinder("course")
	private void initCourseBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.addValidators(cValidator);

	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home/login";

	}

	/**
	 * COURSE CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/history")
	public ModelAndView employeeCourseHistory(HttpSession session) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		ModelAndView mav = new ModelAndView("login");
		if (usession.getUser() != null) {
			mav = new ModelAndView("staff-course-history");
			System.out.println(usession.getEmployee());
			if (cService.findCoursesByEID(usession.getEmployee().getEmployeeId()).size() > 0) {
				mav.addObject("chistory", cService.findCoursesByEID(usession.getEmployee().getEmployeeId()));
			}
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/course/create", method = RequestMethod.GET)
	public ModelAndView newCoursePage() {
		ModelAndView mav = new ModelAndView("staff-course-new");
		mav.addObject("course", new Course());
		return mav;
	}

	@RequestMapping(value = "/course/create", method = RequestMethod.POST)
	public ModelAndView createNewCourse(@ModelAttribute @Valid Course course, BindingResult result,
			HttpSession session) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		if (result.hasErrors())
			return new ModelAndView("staff-course-new");
		ModelAndView mav = new ModelAndView();
		String message = "New course " + course.getCourseId() + " was successfully created.";
		System.out.println(message);
		course.setEmployeeId(usession.getEmployee().getEmployeeId());
		course.setStatus(CourseEventEnum.SUBMITTED);
		mav.setViewName("forward:/staff/history");
		CourseEvent ce = new CourseEvent();
		ce.setCourse(course);
		ce.setEventBy(usession.getEmployee().getEmployeeId());
		ce.setEventType(CourseEventEnum.SUBMITTED);
		ce.setTimeStamp(Calendar.getInstance().getTime());
		cService.createCourse(course);
		ceService.createCourseEvent(ce);
		return mav;
	}

	@RequestMapping(value = "/course/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editCoursePage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("staff-course-edit");
		Course course = cService.findCourse(id);
		mav.addObject("course", course);
		return mav;
	}

	@RequestMapping(value = "/course/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editCourse(@ModelAttribute @Valid Course course, BindingResult result, @PathVariable Integer id,
			HttpSession session) throws CourseNotFound {
		UserSession usession = (UserSession) session.getAttribute("usession");
		if (result.hasErrors())
			return new ModelAndView("staff-course-edit");
		ModelAndView mav = new ModelAndView();
		System.out.println("DATES****" + course.getFromDate() + course.getToDate());
		String message = "New course " + course.getCourseId() + " was successfully created.";
		System.out.println(message);
		course.setEmployeeId(usession.getEmployee().getEmployeeId());
		course.setStatus(CourseEventEnum.UPDATED);
		mav.setViewName("forward:/staff/history");
		CourseEvent ce = new CourseEvent();
		ce.setCourse(course);
		ce.setEventBy(usession.getEmployee().getEmployeeId());
		ce.setEventType(CourseEventEnum.UPDATED);
		ce.setTimeStamp(Calendar.getInstance().getTime());
		cService.changeCourse(course);
		ceService.createCourseEvent(ce);
		return mav;
	}

	@RequestMapping(value = "/course/withdraw/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable Integer id, HttpSession session) throws CourseNotFound {
		UserSession usession = (UserSession) session.getAttribute("usession");
		ModelAndView mav = new ModelAndView("forward:/staff/history");
		Course course = cService.findCourse(id);
		String message = "Course " + course.getCourseId() + " was successfully withdrawn.";
		System.out.println(message);
		course.setStatus(CourseEventEnum.WITHDRAWN);
		CourseEvent ce = new CourseEvent();
		ce.setCourse(course);
		ce.setEventBy(usession.getEmployee().getEmployeeId());
		ce.setEventType(CourseEventEnum.WITHDRAWN);
		ce.setTimeStamp(Calendar.getInstance().getTime());
		cService.changeCourse(course);
		ceService.createCourseEvent(ce);
		return mav;
	}

}
