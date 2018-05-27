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
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "information_id", nullable = false)
	private Information info;
	
	@Column (name = "grade")
	private float grade;
	
	public Rating() {
		
	}
	
	public Rating( User user, Information info, float grade) {
		super();
		this.user = user;
		this.info = info;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Information getInfo() {
		return info;
	}
	public void setInfo(Information info) {
		this.info = info;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	
	
}
