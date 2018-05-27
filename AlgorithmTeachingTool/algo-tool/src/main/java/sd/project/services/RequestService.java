package sd.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dal.dao.AlgorithmDAO;
import dal.dao.InformationDAO;
import dal.dao.LanguageDAO;
import dal.dao.RequestDAO;
import dal.dao.UserDAO;
import dal.model.Algorithm;
import dal.model.Information;
import dal.model.Language;
import dal.model.Rating;
import dal.model.Request;
import dal.model.User;
import sd.project.converters.RepresentationConverter;
import sd.project.dto.RatingDTO;
import sd.project.dto.RequestDTO;

@Service
public class RequestService {

	private RequestDAO requestDAO;
	
	public RequestService() {
		
		requestDAO = new RequestDAO();
	}
	
	public List<RequestDTO> getAllRequests() {
		
		List<Request> allRequests  = requestDAO.getAllRequests();
		List<RequestDTO> dtos = new ArrayList<RequestDTO>();
		for(Request request : allRequests) {
			
			dtos.add(RepresentationConverter.transformRequest(request));
		}
		return dtos;
	}
	
	public List<RequestDTO> getAllRequestsForAlgorithm(String name) {
		
		List<Request> allRequests  = requestDAO.getAllRequests();
		List<RequestDTO> dtos = new ArrayList<RequestDTO>();
		for(Request request : allRequests) {
			
			if(request.getInformation().getAlgorithm().getName().equals(name))
				dtos.add(RepresentationConverter.transformRequest(request));
		}
		return dtos;
	}
	
	public List<RequestDTO> getAllRequestsForUser(String name) {
		
		List<Request> allRequests  = requestDAO.getAllRequests();
		List<RequestDTO> dtos = new ArrayList<RequestDTO>();
		for(Request request : allRequests) {
			
			if(request.getInformation().getAlgorithm().getAdmin().getName().equals(name))
				dtos.add(RepresentationConverter.transformRequest(request));
		}
		return dtos;
	}
	
	public List<RequestDTO> getAllRequestsForUserValidate(String name) {
		
		List<Request> allRequests  = requestDAO.getAllRequests();
		List<RequestDTO> dtos = new ArrayList<RequestDTO>();
		for(Request request : allRequests) {
			
			if(request.getInformation().getAlgorithm().getAdmin().getName().equals(name)&&request.getInformation().isValidated()==false)
				dtos.add(RepresentationConverter.transformRequest(request));
		}
		return dtos;
	}
	
	public RequestDTO findById(int id) {
		
		return RepresentationConverter.transformRequest(requestDAO.findById(id));
	}
	public List<RequestDTO> getAllRequestsByUser(String name) {
		
		List<Request> allRequests  = requestDAO.getAllRequests();
		List<RequestDTO> dtos = new ArrayList<RequestDTO>();
		for(Request request : allRequests) {
			
			if(request.getUser().getName().equals(name))
				dtos.add(RepresentationConverter.transformRequest(request));
		}
		return dtos;
	}
	
	public void submitRequest(String email, String algoId, String languageId, String text) {
		
		UserDAO userDAO = new UserDAO();
		AlgorithmDAO algoDAO = new AlgorithmDAO();
		LanguageDAO languageDAO = new LanguageDAO();
		User user = userDAO.findByAnything("email", email);
		Algorithm algo = algoDAO.findById(Integer.parseInt(algoId));
		Language language = languageDAO.findById(Integer.parseInt(languageId));
		Information info = new Information();
		info.setAlgorithm(algo);
		info.setUser(user);
		info.setText(text);
		info.setLanguage(language);
		info.setRated(0);
		info.setRating(0.0f);
		info.setValidated(false);
		InformationDAO infoDAO = new InformationDAO();
		infoDAO.insertInformation(info);
		Request request = new Request();
		request.setInformation(info);
		request.setUser(user);
		requestDAO.insertRequest(request);
		}
	
	public void addRequest(RequestDTO request) {
		
		requestDAO.insertRequest(RepresentationConverter.inverseTransformRequest(request));
	}
	
	public void updateRequest(RequestDTO request) {
		
		requestDAO.updateRequest(RepresentationConverter.inverseTransformRequest(request));
	}
	
	public void deleteRequest(int id) {
		
		requestDAO.deleteRequest(id);
		
	}
}
