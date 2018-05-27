package dal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "algorithm")
public class Algorithm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "admin", nullable = false)
	private User admin;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "algotodiscipline", joinColumns = {@JoinColumn(name = "algorithm_id", referencedColumnName = "id")}, 
	inverseJoinColumns = {@JoinColumn(name = "discipline_id", referencedColumnName = "id")})
	private Set<Discipline> disciplines;
	
	@OneToMany(mappedBy = "algorithm", fetch = FetchType.EAGER)
	private Set<Information> informations = new HashSet();
	
	public Algorithm() {
		
	}
	
	public Algorithm(String name, User admin, Set<Discipline> disciplines) {
		super();
		this.name = name;
		this.admin = admin;
		this.disciplines = disciplines;
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
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public Set<Discipline> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
	
	
}
