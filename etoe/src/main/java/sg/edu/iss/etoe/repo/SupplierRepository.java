package sg.edu.iss.etoe.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.etoe.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
