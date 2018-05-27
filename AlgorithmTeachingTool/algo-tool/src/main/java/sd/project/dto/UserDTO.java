package sd.project.dto;

public class UserDTO {

	private int id;
	private String name;
	private String email;
	private String password;
	private boolean admin;
	private float rating;
	private int rated;
	
	public UserDTO() {
		
		
	}
	public UserDTO(int id, String name, String email, String password, boolean a, float rating, int rated) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.admin = a;
		this.rating = rating;
		this.rated = rated;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getRated() {
		return rated;
	}
	public void setRated(int rated) {
		this.rated = rated;
	}
}
