package sg.edu.iss.etoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.etoe.model.User;
import sg.edu.iss.etoe.repo.UserRepository;

@Service
public class UserImplementation implements UserInterface {

	@Autowired
	UserRepository urepo;

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		urepo.save(user);

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		urepo.save(user);
	}

	@Override
	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return urepo.findAll();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		urepo.delete(user);

	}

	@Override
	public boolean authenticate(User user) {
		// TODO Auto-generated method stub
		User fromDB = urepo.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (fromDB.getUserName().equals(user.getUserName()) && fromDB.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
   		return urepo.findUserByUserName(name);
	}

}
