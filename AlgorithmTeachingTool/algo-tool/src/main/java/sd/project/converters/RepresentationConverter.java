package sd.project.converters;

import sd.project.dto.*;
import dal.model.*;

//FACADE DESIGN PATTER 

public class RepresentationConverter {

	private static UserConverter userConverter = new UserConverter();
	private static AlgorithmConverter algorithmConverter = new AlgorithmConverter();
	private static InformationConverter informationConverter = new InformationConverter();
	private static DisciplineConverter disciplineConverter = new DisciplineConverter();
	private static RatingConverter ratingConverter = new RatingConverter();
	private static RequestConverter requestConverter = new RequestConverter();
	private static LanguageConverter languageConverter = new LanguageConverter();
	
	public static UserDTO transformUser(User u) {
		
		return userConverter.transform(u);
	}
	
	public static User inverseTransformUser(UserDTO u) {
		
		return userConverter.inverseTransform(u);
	}
	
	public static DisciplineDTO transformDiscipline(Discipline D) { 
		
		return disciplineConverter.transform(D);
	}
	
	public static Discipline inverseTransformDiscipline(DisciplineDTO D) {
		
		return disciplineConverter.inverseTransform(D);
	}
	
	public static LanguageDTO transformLanguage(Language l) {
		
		return languageConverter.transform(l);
	}
	
	public static Language inverseTransformLanguage(LanguageDTO l) {
		
		return languageConverter.inverseTransform(l);
	}
	
	public static InformationDTO transformInformation(Information I) {
		
		return informationConverter.transform(I);
	}
	
	public static Information inverseTransformInformation(InformationDTO I) {
		
		return informationConverter.inverseTransform(I);
	}
	
	public static AlgorithmDTO transformAlgorithm(Algorithm A) {
		
		return algorithmConverter.transform(A);
	}
	
	public static Algorithm inverseTransformAlgorithm(AlgorithmDTO A) {
		
		return algorithmConverter.inverseTransform(A);
	}
	
	public static RatingDTO transformRating(Rating R) {
		
		return ratingConverter.transform(R);
		
	}
	
	public static Rating inverseTransformRating(RatingDTO R) {
		
		return ratingConverter.inverseTransform(R);
	}
	
	public static RequestDTO transformRequest(Request R) {
		
		return requestConverter.transform(R);
	}
	
	public static Request inverseTransformRequest(RequestDTO R) {
		
		return requestConverter.inverseTransform(R);
	}
}

