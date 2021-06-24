package sg.edu.iss.reactive.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.test.StepVerifier;

@SpringBootTest
public class SetUpTest {
	
	@Autowired
	private ProductRepository prepo;
	
	@Test
	public void setUpTest() {
		StepVerifier.create(prepo.deleteAll()).verifyComplete();

		Product product = new Product(1, "test", 1, 20);
		StepVerifier.create(prepo.save(product)).expectNextMatches(createdEntity -> {
			Product p = createdEntity;
			return areProductEqual(p, product);
		}).verifyComplete();
		
	}

	private boolean areProductEqual(Product product, Product product2) {
		// TODO Auto-generated method stub
		return (product.getId()==product2.getId()
				&& product.getProductId()==product2.getProductId()
				&& product.getName().equals(product2.getName())
				&& product.getVersion()==product2.getVersion()
				&& product.getWeight()==product2.getWeight());
	}
	
	

}
