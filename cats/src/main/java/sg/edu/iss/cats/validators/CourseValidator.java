package sg.edu.iss.cats.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.iss.cats.model.Course;

@Component
public class CourseValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Course.class.isAssignableFrom(arg0);

	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Course course = (Course) arg0;	
		if ((course.getFromDate()!=null && course.getToDate()!=null)&&(course.getFromDate().compareTo(course.getToDate()) > 0)) {
			arg1.reject("toDate", "End date should be greater than start date.");
			arg1.rejectValue("toDate", "error.dates", "to date must be > from date");
	
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "courseName", "error.courseName", "Course name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "fromDate", "error.fromDate", "From Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "toDate", "error.toDate", "To Date is required.");
	}

}
