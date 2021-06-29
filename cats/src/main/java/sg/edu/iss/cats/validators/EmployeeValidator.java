package sg.edu.iss.cats.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.iss.cats.model.Employee;
@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Employee.class.isAssignableFrom(arg0);

	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Employee employee = (Employee) arg0;
		ValidationUtils.rejectIfEmpty(arg1, "employeeId", "error.employee.employeeid.empty");
		//ValidationUtils.rejectIfEmpty(arg1, "managerId", "error.employee.managerid.empty");
		ValidationUtils.rejectIfEmpty(arg1, "name", "error.employee.name.empty");
		System.out.println(employee.toString());
	}

}
