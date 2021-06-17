package sg.edu.iss.club.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import sg.edu.iss.club.repo.BookingRepository;
import sg.edu.iss.club.repo.FacilityRepository;
import sg.edu.iss.club.repo.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ClubSimpleJpaSolutionApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingTests {

	@Autowired
	private FacilityRepository frepo;

	@Autowired
	private MemberRepository mrepo;

	@Autowired
	private BookingRepository brepo;

	@Test
	@Order(1)
	public void testBookingCreation() {
		// given
		Facility f = new Facility("facility", "description");
		frepo.save(f);
		Member m = new Member("firstname", "secondname", "thirdname", "test", "test");
		mrepo.save(m);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String startdate = "21/06/2021";
		String enddate = "22/06/2021";
		// convert String to LocalDate
		LocalDate slocalDate = LocalDate.parse(startdate, formatter);
		LocalDate elocalDate = LocalDate.parse(enddate, formatter);
		Booking b = new Booking(slocalDate, elocalDate, "Conference", BookingStatus.BOOKED);
		// when
		Booking saved = brepo.save(b);
		// then
		assertNotNull(saved);
	}

	@Test
	@Order(2)
	public void testDeleteMember() {
		// given
		String c = "Conference";
		// when
		Booking selected = brepo.findBookingByCommentsLike(c).get(0);
		brepo.delete(selected);
		// then
		assertTrue(brepo.findBookingByCommentsLike(c).size() == 0);
	}

}
