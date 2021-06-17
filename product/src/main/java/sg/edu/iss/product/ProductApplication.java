package sg.edu.iss.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import sg.edu.iss.product.model.Product;
import sg.edu.iss.product.repo.ProductRepository;

@SpringBootApplication
public class ProductApplication {

	@Autowired
	ProductRepository prepo;
	
	@DateTimeFormat (pattern="dd-MM-yyyy")
	private LocalDate  toDate = LocalDate.of(2022,11,3);
   
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> { 
//			Product p1 = new Product("Web Camera", "Logitech", "Some stupid camera", 50.0, toDate);
//			Product p2 = new Product("Mic", "Kiki", "Some microphone", 50.0, null);
//			Product p3 = new Product("Speaker", "Bose", "Some wifi speaker", 50.0, null);
//			Product p4 = new Product("GPU Cluster", "Dell", "PC", 5000.0, null);
//			List<Product> plist = new ArrayList<Product>();
//			plist.add(p1);plist.add(p2);plist.add(p3);plist.add(p4);
//			prepo.saveAll(plist);
		};
	}
}
