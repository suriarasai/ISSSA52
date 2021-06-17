package sg.edu.iss.club.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.club.ClubSimpleJpaSolutionApplication;
import sg.edu.iss.club.repo.FacilityRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ClubSimpleJpaSolutionApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FacilityTests {

	@Autowired
	private FacilityRepository frepo;

	@Test
	@Order(1)
	public void testFacilityCreation() {
		// given
		Facility f = new Facility("facility", "description");
		// when
		Facility saved = frepo.save(f);
		// then
		assertNotNull(saved);
	}

	@Test
	@Order(2)
	public void testFindFacilityByName() {
		// given
		String fn = "facility";
		// when
		List<Facility> saved = frepo.findFacilitiesByName(fn);
		// then
		assertTrue(saved.size() > 0);
	}

	@Test
	@Order(3)
	public void testUpdateFacility() {
		// given
		String n = "facility";
		Facility given = frepo.findFacilitiesByName(n).get(0);
		// when
		given.setName("FACILITY");
		Facility saved = frepo.save(given);
		// then
		assertNotNull(saved);

	}

	@Test
	@Order(4)
	public void testListMembers() {
		// given
		List<Facility> list = new ArrayList<Facility>();
		// when
		list = frepo.findAll();
		// then
		assertTrue(list.size() > 0);
	}

	@Test
	@Order(5)
	public void testDeleteMember() {
		// given
		String fn = "FACILITY";
		// when
		Facility selected = frepo.findFacilitiesByName(fn).get(0);
		frepo.delete(selected);
		// then
		assertTrue(frepo.findFacilitiesByName(fn).size() == 0);
	}

}