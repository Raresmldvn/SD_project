package sd.project.converters;

import dal.model.Information;
import sd.project.dto.InformationDTO;

public class InformationConverter {

	private LanguageConverter lanConverter = new LanguageConverter();
	private AlgorithmConverter algConverter = new AlgorithmConverter();
	private UserConverter userConverter = new UserConverter();
	
	public InformationDTO transform(Information I) {
		
		InformationDTO dto = new InformationDTO(I.getId(), I.getText(), lanConverter.transform(I.getLanguage()),algConverter.transform(I.getAlgorithm()), userConverter.transform(I.getUser()), I.getRating(), I.getRated(), I.isValidated());
		return dto;
	}
	
	public Information inverseTransform(InformationDTO I) {
		
		Information info = new Information();
		info.setId(I.getId());
		info.setText(I.getText());
		info.setAlgorithm(algConverter.inverseTransform(I.getAlgorithm()));
		info.setUser(userConverter.inverseTransform(I.getAdmin()));
		info.setLanguage(lanConverter.inverseTransform(I.getLanguage()));
		info.setRated(I.getRated());
		info.setRating(I.getRating());
		info.setValidated(I.isValidated());
		return info;
	}
}
