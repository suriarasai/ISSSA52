package sg.edu.iss.product.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.product.model.Product;
import sg.edu.iss.product.repo.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductRepository prepo;
	
	@GetMapping("/list")
	public String listProducts(Model model) {
		ArrayList<Product> plist = (ArrayList<Product>) prepo.findAll();
		model.addAttribute("plist", plist);
		return "products";
	}

}
