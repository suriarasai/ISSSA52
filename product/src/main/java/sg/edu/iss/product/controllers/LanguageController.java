package sg.edu.iss.product.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {
    //Internationalization i18n
	// Kubernetes k8s
	@GetMapping("/language")
	public String getpage() {
		return "language";
	}
	
	 
}
