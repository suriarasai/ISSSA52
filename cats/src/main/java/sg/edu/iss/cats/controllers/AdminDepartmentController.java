package sg.edu.iss.cats.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.cats.exception.DepartmentNotFound;
import sg.edu.iss.cats.model.Department;
import sg.edu.iss.cats.services.DepartmentService;
import sg.edu.iss.cats.services.EmployeeService;
import sg.edu.iss.cats.validators.DepartmentValidator;

@Controller
@RequestMapping(value="/admin/department")
public class AdminDepartmentController {
	
	@Autowired
	private DepartmentService dService;
	@Autowired
	private EmployeeService eService;
 	@Autowired
	private DepartmentValidator dValidator;
 	
 	@InitBinder("department")
	private void initDepartmentBinder(WebDataBinder binder) {
		binder.addValidators(dValidator);
	}
	/**
	 * DEPARTMENT CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newDepartmentPage() {
		ModelAndView mav = new ModelAndView("department-new", "department", new Department());
		mav.addObject("eidlist", eService.findAllEmployeeIDs());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewDepartment(@ModelAttribute @Valid Department department, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("department-new");

		ModelAndView mav = new ModelAndView("forward:/admin/department/list");
		dService.createDepartment(department);
		return mav;
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelAndView departmentListPage() {
		ModelAndView mav = new ModelAndView("department-list");
		List<Department> departmentList = dService.findAllDepartments();
		mav.addObject("departmentList", departmentList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editDepartmentPage(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("department-edit");
		Department department = dService.findDepartment(id);
		mav.addObject("department", department);
		mav.addObject("eidlist", eService.findAllEmployeeIDs());
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editDepartment(@ModelAttribute @Valid Department department, BindingResult result,
			@PathVariable String id) throws DepartmentNotFound {

		if (result.hasErrors())
			return new ModelAndView("department-edit");

		ModelAndView mav = new ModelAndView("forward:/admin/department/list");
		dService.changeDepartment(department);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}")
	public ModelAndView deleteDepartment(@PathVariable("id") String id)
			throws DepartmentNotFound {
		System.out.println(id);
		ModelAndView mav = new ModelAndView("forward:/admin/department/list");
		Department department = dService.findDepartment(id);
		dService.removeDepartment(department);
		return mav;
	}

}
