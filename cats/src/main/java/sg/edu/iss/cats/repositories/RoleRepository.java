package sg.edu.iss.cats.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.cats.model.Role;

public interface RoleRepository extends  JpaRepository<Role, String> {
	
	@Query("SELECT r.name FROM Role r")
	ArrayList<String> findAllRolesNames();
	
	@Query("SELECT r FROM Role r WHERE r.name = :name")
	ArrayList<Role> findRoleByName(@Param("name") String name);
}
