package sg.edu.iss.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.demo.domain.ParkingSpace;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer>{

}
