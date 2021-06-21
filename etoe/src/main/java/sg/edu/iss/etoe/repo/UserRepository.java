package sg.edu.iss.etoe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.etoe.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findUserByUserNameAndPassword(String un, String pwd);
    public User findUserByUserName(String name);

}
