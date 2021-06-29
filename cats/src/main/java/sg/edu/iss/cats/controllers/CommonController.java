package sg.edu.iss.cats.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.cats.model.Employee;
import sg.edu.iss.cats.model.Role;
import sg.edu.iss.cats.model.User;
import sg.edu.iss.cats.services.EmployeeService;
import sg.edu.iss.cats.services.UserService;

@Controller
public class CommonController {
	
	@Autowired
	private UserService uService;

	@Autowired
	private EmployeeService eService;

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/home/login", method = RequestMethod.GET)
	public String logic(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/home/authenticate", method = RequestMethod.GET)
	public String authenticate(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model, HttpSession session) {
		UserSession usession = new UserSession();
		if (bindingResult.hasErrors()) {
			return "login";
		}
		else {
			User u = uService.authenticate(user.getName(), user.getPassword());
			usession.setUser(u);
		    List<Role> rset = u.getRoleSet();
		    System.out.println();
		    for (Iterator<Role> iterator = rset.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				System.out.println(role.toString());
			}
			usession.setEmployee(eService.findEmployeeById(usession.getUser().getEmployeeId()));
			ArrayList<Employee> subordinates = eService.findSubordinates(usession.getUser().getEmployeeId());
			if (subordinates != null) {
				usession.setSubordinates(subordinates);
			}
			session.setAttribute("usession", usession);
			return "forward:/staff/history";
		}

	}
	
}
