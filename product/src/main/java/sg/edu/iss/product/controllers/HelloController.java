package sg.edu.iss.product.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping("/time")
	public String sayHello(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	    Calendar cal = Calendar.getInstance();
	    model.addAttribute("today", dateFormat.format(cal.getTime()));
	    return "hello";
	}

}
