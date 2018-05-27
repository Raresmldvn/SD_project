package sd.project.converters;

import dal.model.Rating;
import sd.project.dto.RatingDTO;

public class RatingConverter {

	private InformationConverter infoConverter = new InformationConverter();
	private UserConverter userConverter = new UserConverter();
	public RatingDTO transform(Rating R) {
		
		RatingDTO dto = new RatingDTO(R.getId(), userConverter.transform(R.getUser()), infoConverter.transform(R.getInfo()), R.getGrade());
		return dto;
	}
	
	public Rating inverseTransform(RatingDTO R) {
		
		Rating rating = new Rating();
		rating.setId(R.getId());
		rating.setUser(userConverter.inverseTransform(R.getUser()));
		rating.setInfo(infoConverter.inverseTransform(R.getInformation()));
		rating.setGrade(R.getGrade());
		return rating;
	}
}
