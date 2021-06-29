package sg.edu.iss.cats.repotest;




import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import sg.edu.iss.cats.model.User;
import sg.edu.iss.cats.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityRepositoryTest {

	//@Autowired
    //private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
    
    
    @Test
    public void whenFindByName_thenReturnUser() {
      
        User dilbert = new User();
        dilbert.setUserId("dilbert");
        ArrayList<String> managernames = userRepository.findManagerNameByUID(dilbert.getUserId());
        for (Iterator<String> iterator = managernames.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println("name:"+string);
		}
        // then
        assertThat(managernames.size()).isGreaterThan(0);
          
    }
	  
	  
}
