package sd.project.converters;

import dal.model.User;
import sd.project.dto.UserDTO;

public class UserConverter {

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
