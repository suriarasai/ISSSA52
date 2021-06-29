package sg.edu.iss.cats.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.cats.helper.Approve;
import sg.edu.iss.cats.helper.CourseEventEnum;
import sg.edu.iss.cats.model.Course;
import sg.edu.iss.cats.model.CourseEvent;
import sg.edu.iss.cats.model.Employee;
import sg.edu.iss.cats.services.CourseEventService;
import sg.edu.iss.cats.services.CourseService;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	@Autowired
	private CourseService cService;

	@Autowired
	private CourseEventService ceService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		
	}
	
	@RequestMapping(value = "/pending")
	public ModelAndView pendingApprovals(HttpSession session) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		HashMap<Employee, ArrayList<Course>> hm = new HashMap<Employee, ArrayList<Course>>();
		System.out.println(usession.toString());
		ModelAndView mav = new ModelAndView("login");
		if (usession.getUser() != null) {
			for (Employee employee : usession.getSubordinates()) {
				ArrayList<Course> clist = cService.findPendingCoursesByEID(employee.getEmployeeId());
				hm.put(employee, clist);
			}
			mav = new ModelAndView("manager-pending-course-history");
			mav.addObject("pendinghistory", hm);
			return mav;
		}
		return mav;

	}
	
	@RequestMapping(value = "/shistory")
	public ModelAndView subordinatesHistory(HttpSession session ) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		HashMap<Employee, ArrayList<Course>> submap = new HashMap<Employee, ArrayList<Course>>();		
		for (Employee subordinate : usession.getSubordinates()) {
			submap.put(subordinate, cService.findCoursesByEID(subordinate.getEmployeeId()));
		}
		
		ModelAndView mav = new ModelAndView("login");
		if (usession.getUser() != null && usession.getSubordinates() != null) {
			mav = new ModelAndView("subordinates-course-history");
			mav.addObject("submap", submap);
			return mav;
		}
		return mav;	
	}

	@RequestMapping(value = "/course/display/{id}", method = RequestMethod.GET)
	public ModelAndView newDepartmentPage(@PathVariable int id) {
		Course course = cService.findCourse(id);
		ModelAndView mav = new ModelAndView("manager-course-detail", "course", course);
		mav.addObject("approve", new Approve());
		return mav;
	}

	@RequestMapping(value = "/course/edit/{id}", method = RequestMethod.POST)
	public ModelAndView approveOrRejectCourse(@ModelAttribute("approve") @Valid Approve approve, BindingResult result,
			@PathVariable Integer id, HttpSession session) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		if (result.hasErrors())
			return new ModelAndView("manager-course-detail");
		Course c = cService.findCourse(id);
		CourseEvent ce = new CourseEvent();
		if (approve.getDecision().trim().equalsIgnoreCase(CourseEventEnum.APPROVED.toString())) {
			ce.setEventType(CourseEventEnum.APPROVED);
			c.setStatus(CourseEventEnum.APPROVED);
		} else {
			ce.setEventType(CourseEventEnum.REJECTED);
			c.setStatus(CourseEventEnum.REJECTED);
		}
		ce.setEventBy(usession.getEmployee().getEmployeeId());
		ce.setComment(approve.getComment());
		ce.setTimeStamp(Calendar.getInstance().getTime());
		ce.setCourse(c);
		ArrayList<CourseEvent> celist = c.getEvents();
		celist.add(ce);
		c.setEvents(celist);
		System.out.println(c.toString());
		cService.changeCourse(c);
		ceService.createCourseEvent(ce);
		ModelAndView mav = new ModelAndView("forward:/manager/pending");
		String message = "Course was successfully updated.";
		System.out.println(message);
		return mav;
	}
	
	
}
