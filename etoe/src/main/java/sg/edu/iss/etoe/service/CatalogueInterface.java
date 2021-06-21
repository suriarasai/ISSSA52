package sg.edu.iss.etoe.service;

import java.util.List;

import sg.edu.iss.etoe.model.Product;

public interface CatalogueInterface {
	
	public void save(Product product);
	public List<Product> list();
	

}
