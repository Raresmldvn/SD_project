package sd.project.dto;

public class InformationDTO {

	private int id;
	private String text;
	private LanguageDTO language;
	private AlgorithmDTO algorithm;
	private UserDTO admin;
	private float rating;
	private int rated;
	private boolean validated;
	
	private String algorithmName;
	private String languageName;
	private String userName;
	
	public InformationDTO() {
		
		
	}

	public InformationDTO(int id, String text, LanguageDTO language, AlgorithmDTO algorithm, UserDTO admin,
			float rating, int rated, boolean validated) {
		super();
		this.id = id;
		this.text = text;
		this.language = language;
		this.algorithm = algorithm;
		this.admin = admin;
		this.rating = rating;
		this.rated = rated;
		this.validated = validated;
		this.algorithmName = algorithm.getName();
		this.languageName = language.getName();
		this.userName = admin.getName();
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
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

	public LanguageDTO getLanguage() {
		return language;
	}

	public void setLanguage(LanguageDTO language) {
		this.language = language;
	}

	public AlgorithmDTO getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(AlgorithmDTO algorithm) {
		this.algorithm = algorithm;
		this.algorithmName = algorithm.getName();
	}

	public UserDTO getAdmin() {
		return admin;
		
	}

	public void setAdmin(UserDTO admin) {
		this.admin = admin;
		this.userName = admin.getName();
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
