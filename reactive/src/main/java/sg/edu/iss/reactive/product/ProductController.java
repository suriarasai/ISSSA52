package sg.edu.iss.reactive.product;

import org.springframework.beans.BeanUtils;
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
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository prepo;
	
	@GetMapping("/products")
	public Flux<Product> getAllProducts() {
		return prepo.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Mono<Product> getProduct(@PathVariable Integer id) {
		return prepo.findById(id);
	}
	
	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> saveProduct(@RequestBody Product product) {
		return prepo.save(product);
	}
	
	@PutMapping("/products/{id}")
	public Mono<ResponseEntity<Product>> updateProduct(@PathVariable(value = "id") Integer id,
			@RequestBody Product product) {
		return prepo.findById(id).flatMap(existingProduct -> {
			//existingProduct.setId(product.getId());
			BeanUtils.copyProperties(product, existingProduct);
			return prepo.save(existingProduct);
		}).map(updateEmployee -> new ResponseEntity<>(updateEmployee, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/products/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<ResponseEntity<Object>> deleteProduct(@PathVariable(value = "id") Integer id) {
		return prepo.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/products")
	public Mono<Void> deleteAllProducts() {
		return prepo.deleteAll();
	}
}
