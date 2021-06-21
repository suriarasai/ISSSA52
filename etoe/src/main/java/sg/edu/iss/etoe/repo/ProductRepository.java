package sg.edu.iss.etoe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.etoe.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
