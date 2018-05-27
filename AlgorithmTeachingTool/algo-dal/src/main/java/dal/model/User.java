package dal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name="admin")
	private boolean admin;
	
	@Column(name="rating")
	private float rating;
	
	@Column(name="rated")
	private int rated;
	
	@OneToMany(mappedBy = "admin", fetch = FetchType.EAGER)
	private Set<Algorithm> algorithms = new HashSet();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Rating> ratings = new HashSet();
	
	@OneToMany(mappedBy = "information", fetch = FetchType.EAGER)
	private Set<Request> requests= new HashSet();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Information> informations = new HashSet();
	
	
	public User() {
		

	}
	public User(String name, String email, String password, boolean admin, float rating, int rated) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.admin = admin;
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
	public Set<Algorithm> getAlgorithms() {
		return algorithms;
	}
	public void setAlgorithms(Set<Algorithm> algorithms) {
		this.algorithms = algorithms;
	}
	
}
