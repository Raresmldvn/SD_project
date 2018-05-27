package sd.project.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.dao.UserDAO;
import dal.model.User;
import sd.project.converters.RepresentationConverter;
import sd.project.dto.UserDTO;

@Service
public class UserService {

	private UserDAO userDAO;
	
	@Autowired
	public UserService() {
		
		userDAO = new UserDAO();
	}
	
	
	public List<UserDTO> getAllUsers() {
		
		List<User> users = userDAO.getAllUsers();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for(User user : users) {
			
			dtos.add(transform(user));
		}
		return dtos;
	}
	
	public List<UserDTO> getAllUsersIncreasingRating() {
		

		List<User> users = userDAO.getAllUsers();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for(User user : users) {
			
			dtos.add(transform(user));
		}
		Collections.sort(dtos, new Comparator<UserDTO>() {

	        public int compare(UserDTO o1, UserDTO o2) {
	           if(o1.getRating()<o2.getRating())
	        	   return 1;
	           else
	        	   return -1;
	        }
	    });
		return dtos;
	}
	
	public UserDTO findById(int id) {
		
		return RepresentationConverter.transformUser(userDAO.finById(id));
	}
	public UserDTO getUserByName(String name) {
		
		User user = userDAO.findByAnything("name", name);
		return transform(user);
	}
	
	public UserDTO getUserByEmail(String email) {
		
		User user = userDAO.findByAnything("email", email);
		return transform(user);
	}
	
	public void addUser(UserDTO u) {
		
		userDAO.insertUser(inverseTransform(u));
	}
	
	public void updateUser(UserDTO u) {
		
		userDAO.updateUser(inverseTransform(u));
	}
	
	public void deleteUser(int id) {
		
		userDAO.deleteUser(id);
	}
	
	public UserDTO transform(User u) {
		
		if(u==null)
			return null;
		UserDTO dto = new UserDTO(u.getId(), u.getName(), u.getEmail(),u.getPassword(),u.isAdmin(), u.getRating(), u.getRated());
		return dto;
	}
	
	public User inverseTransform(UserDTO u) {
		
		if(u==null)
			return null;
		User user = new User(u.getName(), u.getEmail(),u.getPassword(),u.isAdmin(), u.getRating(), u.getRated());
		user.setId(u.getId());
		return user;
	}
}
