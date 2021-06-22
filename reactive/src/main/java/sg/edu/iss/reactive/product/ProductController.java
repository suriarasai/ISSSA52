package sg.edu.iss.reactive.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository prepo;
	
	@GetMapping
	public Flux<Product> getAllProducts() {
		return prepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Product> getProduct(@PathVariable String id) {
		return prepo.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> saveProduct(@RequestBody Product product) {
		return prepo.save(product);
	}
	
	/*
	 * @PutMapping("/{id}") public Mono<Product>
	 * updateProduct(@PathVariable(value="id") String id,
	 * 
	 * @RequestBody Product product) { return
	 * prepo.findById(id).flatMap(existingProduct -> {
	 * existingProduct.setId(product.getId());
	 * existingProduct.setProductId(product.getProductId());
	 * existingProduct.setName(product.getName());
	 * existingProduct.setVersion(product.getVersion());
	 * existingProduct.setWeight(product.getWeight()); prepo.save(existingProduct);
	 * }).map(updateProduct -> new ResponseEntity<>(existingProduct, HttpStatus.OK))
	 * .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)); }
	 */
	
	//@DeleteMapping("/{id}")
	
}
