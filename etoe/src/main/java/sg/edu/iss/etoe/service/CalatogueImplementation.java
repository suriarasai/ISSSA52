package sg.edu.iss.etoe.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.etoe.model.Product;
import sg.edu.iss.etoe.repo.ProductRepository;
import sg.edu.iss.etoe.repo.SupplierRepository;

@Service
public class CalatogueImplementation implements CatalogueInterface {
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	SupplierRepository srepo;

	@Transactional
	public void save(Product product) {
		// TODO Auto-generated method stub
		prepo.save(product);
		
	}

	@Transactional(timeout = 30, readOnly = true)
	public List<Product> list() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	
}
