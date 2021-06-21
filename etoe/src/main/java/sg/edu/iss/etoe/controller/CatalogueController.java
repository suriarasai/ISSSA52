package sg.edu.iss.etoe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.val;
import sg.edu.iss.etoe.model.Product;
import sg.edu.iss.etoe.service.CatalogueInterface;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
	
	@Autowired
	private CatalogueInterface cservice;
	
	@Autowired
	public void setCatalogue(CatalogueInterface cs) {
		this.cservice = cs;
	}
	
	@RequestMapping(value="showform")
	public String showForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "catalogueform";
	}
	
	@RequestMapping(value = "save")
	public String saveProduct(@ModelAttribute("product") Product product) 
	{
		cservice.save(product);
		return "forward:/catalogue/list";
	}
	
	@RequestMapping(value="list")
	public String listProduct(Model model) {
		ArrayList<Product> plist = (ArrayList<Product>) cservice.list();
		model.addAttribute("plist", plist);
		return "catalogue";
	
	}

}
