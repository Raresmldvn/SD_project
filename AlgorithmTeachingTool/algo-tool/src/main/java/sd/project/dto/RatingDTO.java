package sd.project.dto;

public class RatingDTO {

	private int id;
	private UserDTO user;
	private InformationDTO information;
	private float grade;
	
	
	public RatingDTO(int id, UserDTO user, InformationDTO information, float grade) {
		super();
		this.id = id;
		this.user = user;
		this.information = information;
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public InformationDTO getInformation() {
		return information;
	}
	public void setInformation(InformationDTO information) {
		this.information = information;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	
}
