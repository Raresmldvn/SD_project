package sd.project.converters;

import dal.model.Request;
import sd.project.dto.RequestDTO;

public class RequestConverter {

	private InformationConverter infoConverter = new InformationConverter();
	private UserConverter userConverter = new UserConverter();
	public RequestDTO transform(Request R) {
		
		RequestDTO dto = new RequestDTO(R.getId(), userConverter.transform(R.getUser()), infoConverter.transform(R.getInformation()));
		return dto;
	}
	
	public Request inverseTransform(RequestDTO R) {
		
		Request request = new Request();
		request.setId(R.getId());
		request.setUser(userConverter.inverseTransform(R.getUser()));
		request.setInformation(infoConverter.inverseTransform(R.getInformation()));
		return request;
	}
}
