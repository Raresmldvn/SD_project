package sd.project.dto;

import java.util.Set;

public class AlgorithmDTO {

	private int id;
	private String name;
	private UserDTO user;
	private String admin; 
	private Set<DisciplineDTO> disciplines;

	
	public AlgorithmDTO() {
		
		
	}
	public AlgorithmDTO(int id, String name, UserDTO user, Set<DisciplineDTO> disciplines) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.admin = user.getName();
		this.disciplines = disciplines;
	}
	
	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
		this.admin = user.getName();
	}
	public Set<DisciplineDTO> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(Set<DisciplineDTO> disciplines) {
		this.disciplines = disciplines;
	}
	
	
}
