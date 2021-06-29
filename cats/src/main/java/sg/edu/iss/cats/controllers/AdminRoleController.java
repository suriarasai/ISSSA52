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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.cats.exception.RoleNotFound;
import sg.edu.iss.cats.model.Role;
import sg.edu.iss.cats.services.RoleService;
import sg.edu.iss.cats.validators.RoleValidator;


@Controller
@RequestMapping(value="/admin/role")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminRoleController {
	@Autowired
	private RoleService rService;
	@Autowired
	private RoleValidator rValidator;

	@InitBinder("role")
	private void initRoleBinder(WebDataBinder binder) {
		binder.addValidators(rValidator);
	}

	/**
	 * ROLE CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newRolePage() {
		ModelAndView mav = new ModelAndView("role-new", "role", new Role());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewRole(@ModelAttribute @Valid Role role, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("role-new");

		ModelAndView mav = new ModelAndView();
		String message = "New role " + role.getRoleId() + " was successfully created.";
		System.out.println(message);
		rService.createRole(role);
		mav.setViewName("forward:/admin/role/list");
		return mav;
	}

	@RequestMapping(value = "/list")
	public ModelAndView roleListPage() {
		ModelAndView mav = new ModelAndView("role-list");
		List<Role> roleList = rService.findAllRoles();
		mav.addObject("roleList", roleList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editRolePage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("role-edit");
		Role role = rService.findRole(id);
		mav.addObject("role", role);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editRole(@ModelAttribute @Valid Role role, BindingResult result, 
			@PathVariable String id) throws RoleNotFound {

		if (result.hasErrors())
			return new ModelAndView("role-edit");

		ModelAndView mav = new ModelAndView("forward:/admin/role/list");
		String message = "Role was successfully updated.";
		System.out.println(message);
		rService.changeRole(role);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteRole(@PathVariable String id)
			throws RoleNotFound {

		ModelAndView mav = new ModelAndView("forward:/admin/role/list");
		Role role = rService.findRole(id);
		rService.removeRole(role);
		String message = "The role " + role.getRoleId() + " was successfully deleted.";
		System.out.println(message);
		return mav;
	}

}
