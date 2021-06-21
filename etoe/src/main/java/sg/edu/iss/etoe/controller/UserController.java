package sg.edu.iss.etoe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.etoe.model.User;
import sg.edu.iss.etoe.service.UserInterface;

@Controller
public class UserController {

	@Autowired
	UserInterface uservice;
	
	@Autowired
	public void setUserInterface(UserInterface us) {
		this.uservice = us;
	}
	
	@RequestMapping(path = "/login")
	public String login(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "login";
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(uservice.authenticate(user)) 
		{
			User u = uservice.findByName(user.getUserName());
			session.setAttribute("usession", u);
			return "welcome";
		}
		else
			return "login";
	}
	
}
