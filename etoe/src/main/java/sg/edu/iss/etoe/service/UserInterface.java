package sg.edu.iss.etoe.service;

import java.util.List;

import sg.edu.iss.etoe.model.User;

public interface UserInterface {
	
	public void createUser(User user);
	public void updateUser(User user);
	public List<User> listAllUser();
	public void deleteUser(User user);
	public boolean authenticate(User user);
	public User findByName(String name);


}
