package sd.project.dto;

public class RequestDTO {

	private int id;
	private UserDTO user;
	private InformationDTO information;
	
	private String algorithmName;
	private String languageName;
	private String requesterName;
	public RequestDTO(int id, UserDTO user, InformationDTO information) {
		super();
		this.id = id;
		this.user = user;
		this.information = information;
		this.languageName = information.getLanguageName();
		this.algorithmName = information.getAlgorithmName();
		this.requesterName = user.getName();
	}
	
	
	public String getRequesterName() {
		return requesterName;
	}


	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
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
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
		this.requesterName = user.getName();
	}
	public InformationDTO getInformation() {
		return information;
	}
	public void setInformation(InformationDTO information) {
		this.information = information;
		this.languageName = information.getLanguageName();
		this.algorithmName = information.getAlgorithmName();
	}
	
	
}
