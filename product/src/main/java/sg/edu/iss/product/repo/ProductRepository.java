package sg.edu.iss.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
