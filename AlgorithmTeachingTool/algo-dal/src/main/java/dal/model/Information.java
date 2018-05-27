package dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "information")
public class Information {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "text")
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;
	
	@ManyToOne
	@JoinColumn(name = "algorithm_id", nullable = false)
	private Algorithm algorithm;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name="rating")
	private float rating;
	
	@Column(name="rated")
	private int rated;
	
	@Column(name="validated")
	private boolean validated;
	
	public Information() {
		
		
	}
	public Information(String text, Language language, Algorithm algorithm, User user, float rating, int rated, boolean validated) {
		super();
		this.text = text;
		this.language = language;
		this.algorithm = algorithm;
		this.user = user;
		this.rating = rating;
		this.rated = rated;
		this.validated = validated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Algorithm getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
}
