package sg.edu.iss.cats.controllers;

import java.util.ArrayList;
import java.util.Iterator;
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

import sg.edu.iss.cats.exception.UserNotFound;
import sg.edu.iss.cats.model.Role;
import sg.edu.iss.cats.model.User;
import sg.edu.iss.cats.services.EmployeeService;
import sg.edu.iss.cats.services.RoleService;
import sg.edu.iss.cats.services.UserService;
import sg.edu.iss.cats.validators.UserValidator;


@Controller
@RequestMapping(value="/admin/user")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminUserController {

	@Autowired
	private UserService uService;
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private RoleService rservice;
	
	@Autowired
	private UserValidator uValidator;

	@InitBinder("user")
	private void initUserBinder(WebDataBinder binder) {
		binder.addValidators(uValidator);
	}

	/**
	 * USER CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newUserPage() {
		ModelAndView mav = new ModelAndView("user-new", "user", new User());
		ArrayList<String> eidList = eService.findAllEmployeeIDs();
		mav.addObject("eidlist", eidList);
		ArrayList<Role> roles = rservice.findAllRoles();
		mav.addObject("roles", roles);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute @Valid User user, BindingResult result) {
        ArrayList<Role> newRoleSet = new ArrayList<Role>();
		if (result.hasErrors())
			return new ModelAndView("user-new");
		ModelAndView mav = new ModelAndView();
		//System.out.println("User: "+user.getEmployeeId()+" "+user.getEmployeeId()+" "+user.getName()+" "+user.getPassword());
		for (Iterator<Role> iterator = user.getRoleSet().iterator(); iterator.hasNext();) {
			Role type = (Role) iterator.next();
			Role newRole = rservice.findRole(type.getRoleId());
			newRoleSet.add(newRole);
			//System.out.println("Role: "+type.toString());
		}
		user.setRoleSet(newRoleSet);
		uService.createUser(user);
		mav.setViewName("forward:/admin/user/list");
		return mav;
	}

	@RequestMapping(value = "/list")
	public ModelAndView userListPage() {
		ModelAndView mav = new ModelAndView("user-list");
		List<User> userList = uService.findAllUsers();
		mav.addObject("userList", userList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("user-edit");
		User user = uService.findUser(id);
		ArrayList<Role> roles = rservice.findAllRoles();
		mav.addObject("user", user);
		mav.addObject("roles",roles);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute @Valid User user, BindingResult result, 
			@PathVariable String id) throws UserNotFound {
		if (result.hasErrors())
			return new ModelAndView("user-edit");
		ModelAndView mav = new ModelAndView("forward:/admin/user/list");
		String message = "User was successfully updated.";
		System.out.println(message);
		uService.changeUser(user);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable String id)
			throws UserNotFound {

		ModelAndView mav = new ModelAndView("forward:/admin/user/list");
		User user = uService.findUser(id);
		uService.removeUser(user);
		String message = "The user " + user.getUserId() + " was successfully deleted.";
		System.out.println(message);
		return mav;
	}
}
