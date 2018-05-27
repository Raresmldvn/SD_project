package dal.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "discipline")
public class Discipline {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(mappedBy = "disciplines", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Algorithm> algorithms;
	
	public Discipline() {
		
		
	}
	public Discipline(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
	public Set<Algorithm> getAlgorithms() {
		return algorithms;
	}
	public void setAlgorithms(Set<Algorithm> algorithms) {
		this.algorithms = algorithms;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
