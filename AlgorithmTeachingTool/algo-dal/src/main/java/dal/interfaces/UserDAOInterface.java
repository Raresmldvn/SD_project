package dal.interfaces;

import java.util.ArrayList;

import dal.model.User;

public interface UserDAOInterface {

	public User finById(int id);
	
	public User findByAnything(String column, String value);
	
	public void insertUser(User toBeInserted);
	
	public void updateUser(User toBeUpdated);

	public void deleteUser(int id);
	
	public ArrayList<User> getAllUsers();
}
