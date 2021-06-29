package sg.edu.iss.cats.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.cats.model.Role;
import sg.edu.iss.cats.model.User;
import sg.edu.iss.cats.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserRepository userRepository;
	

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#findAllUsers()
	 */
	@Override
	@Transactional
	public ArrayList<User> findAllUsers() {
		ArrayList<User> ul = (ArrayList<User>) userRepository.findAll();
		return ul;
	}
	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#findUser(java.lang.String)
	 */
	@Override
	@Transactional
	public User findUser(String userId) {
		return userRepository.findById(userId).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#createUser(edu.iss.cats.model.User)
	 */
	@Override
	@Transactional
	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#changeUser(edu.iss.cats.model.User)
	 */
	@Override
	@Transactional
	public User changeUser(User user) {
		return userRepository.saveAndFlush(user);
	}


	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#removeUser(edu.iss.cats.model.User)
	 */
	@Override
	@Transactional
	public void removeUser(User user) {
		userRepository.delete(user);
	}
	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#findRolesForUser(java.lang.String)
	 */
	@Override
	@Transactional
	public ArrayList<Role> findRolesForUser(String userId) {
		return (ArrayList<Role>)userRepository.findById(userId).orElse(null).getRoleSet();
	}
	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#findRoleNamesForUser(java.lang.String)
	 */
	@Override
	@Transactional
	public ArrayList<String> findRoleNamesForUser(String userId) {
		ArrayList<Role> rset =  (ArrayList<Role>) userRepository.findById(userId).orElse(null).getRoleSet();
		ArrayList<String> rnames = new ArrayList<String>();
		for (Role role : rset) {
			rnames.add(role.getName());
		}
		return rnames;
	}
	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.UserService#findManagerNameByUID(java.lang.String)
	 */
	@Override
	@Transactional
	public ArrayList<String> findManagerNameByUID(String userId) {
		return userRepository.findManagerNameByUID(userId);
	}
	
	@Transactional
	public User authenticate(String uname, String pwd) {
		User u = userRepository.findUserByNamePwd(uname, pwd);
		return u;
	}

}
