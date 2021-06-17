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
import sg.edu.iss.club.repo.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ClubSimpleJpaSolutionApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberTests {

	@Autowired
	private MemberRepository mrepo;

	@Test
	@Order(1)
	public void testMemberCreation() {
		// given
		Member m = new Member("firstname", "secondname", "thirdname", "test", "test");
		// when
		Member saved = mrepo.save(m);
		// then
		assertNotNull(saved);
	}

	@Test
	@Order(2)
	public void testFindMemberByFirstName() {
		// given
		String fn = "firstname";
		// when
		List<Member> saved = mrepo.findMemberByFirstName(fn);
		// then
		assertTrue(saved.size() > 0);
	}

	@Test
	@Order(3)
	public void testUpdateMember() {
		// given
		String fn = "firstname";
		Member given = mrepo.findMemberByFirstName(fn).get(0);
		// when
		given.setFirstName("FIRST");
		Member saved = mrepo.save(given);
		// then
		assertNotNull(saved);

	}

	@Test
	@Order(4)
	public void testListMembers() {
		// given
		List<Member> list = new ArrayList<Member>();
		// when
		list = mrepo.findAll();
		// then
		assertTrue(list.size() > 0);
	}

	@Test
	@Order(5)
	public void testDeleteMember() {
		// given
		String fn = "FIRST";
		// when
		Member selected = mrepo.findMemberByFirstName(fn).get(0);
		mrepo.delete(selected);
		// then
		assertTrue(mrepo.findMemberByFirstName(fn).size() == 0);
	}

}