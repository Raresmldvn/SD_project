package sd.project.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dal.dao.InformationDAO;
import dal.dao.RequestDAO;
import dal.model.Information;
import dal.model.Request;
import sd.project.converters.RepresentationConverter;
import sd.project.dto.InformationDTO;
import sd.project.dto.UserDTO;

@Service
public class InformationService {

	private InformationDAO infoDAO;
	private RequestDAO requestDAO;
	@Autowired
	public InformationService() {
		
		this.infoDAO = new InformationDAO();
		this.requestDAO = new RequestDAO();
	}
	
	public List<InformationDTO> getAllInformation() {
		
		List<Information> infos = infoDAO.getAllInformation();
		List<InformationDTO> dtos = new ArrayList<InformationDTO>();
		for(Information info : infos) {
			
			dtos.add(RepresentationConverter.transformInformation(info));
		}
		return dtos;
	}
	
	public List<InformationDTO> getAllInformationRatingIncreasing() {
		
		List<Information> infos = infoDAO.getAllInformation();
		List<InformationDTO> dtos = new ArrayList<InformationDTO>();
		for(Information info : infos) {
			
			dtos.add(RepresentationConverter.transformInformation(info));
		}
		Collections.sort(dtos, new Comparator<InformationDTO>() {

	        public int compare(InformationDTO o1, InformationDTO o2) {
	           if(o1.getRating()<o2.getRating())
	        	   return 1;
	           else
	        	   return -1;
	        }
	    });
		return dtos;
	}
	
	public List<InformationDTO> getForAlgorithm(String name) {
		
		List<Information> infos = infoDAO.getAllInformation();
		List<InformationDTO> dtos = new ArrayList<InformationDTO>();
		for(Information info : infos) {
			
			if(info.getAlgorithm().getName().equals(name))
				dtos.add(RepresentationConverter.transformInformation(info));
		}
		return dtos;
	}
	
	public List<InformationDTO> getForAlgorithmValidated(String name) {
		
		List<Information> infos = infoDAO.getAllInformation();
		List<InformationDTO> dtos = new ArrayList<InformationDTO>();
		for(Information info : infos) {
			if(info.getAlgorithm().getName().equals(name)&&info.isValidated()==true)
				dtos.add(RepresentationConverter.transformInformation(info));
		}
		return dtos;
	}

	public List<InformationDTO> getForAlgorithmUnvalidated(String name) {
	
	List<Information> infos = infoDAO.getAllInformation();
	List<InformationDTO> dtos = new ArrayList<InformationDTO>();
	for(Information info : infos) {
		
		if(info.getAlgorithm().getName().equals(name)&&info.isValidated()==false)
			dtos.add(RepresentationConverter.transformInformation(info));
	}
	return dtos;
}
	
	public InformationDTO findById(int id) {
		
		return RepresentationConverter.transformInformation(infoDAO.findById(id));
	}
	public List<InformationDTO> getForUser(String name) {
		
		List<Information> infos = infoDAO.getAllInformation();
		List<InformationDTO> dtos = new ArrayList<InformationDTO>();
		for(Information info : infos) {
			
			if(info.getUser().getName().equals(name))
				dtos.add(RepresentationConverter.transformInformation(info));
		}
		return dtos;
	}
	
	public InformationDTO getInfoForAlgorithmAndLanguage(String nameAlgorithm, String nameLanguage) {
		
		List<Information> infos = infoDAO.getAllInformation();
		for(Information info : infos) {
			
			if(info.getAlgorithm().getName().equals(nameAlgorithm)&&info.getLanguage().getName().equals(nameLanguage))
				return RepresentationConverter.transformInformation(info);
		}
		return null;
	}
	
	public void validate(String id) {
		
		Request request = requestDAO.findById(Integer.parseInt(id));
		Information info = infoDAO.findById(request.getInformation().getId());
		info.setValidated(true);
		System.out.println(info.getId() + " " + request.getId());
		infoDAO.updateInformation(info);
	}
	
	public void reject(String id) {
		
		Request request = requestDAO.findById(Integer.parseInt(id));
		Information info = infoDAO.findById(request.getInformation().getId());
		infoDAO.deleteInformation(info.getId());
		requestDAO.deleteRequest(Integer.parseInt(id));
		
	}
	public void addInformation(InformationDTO i) {
		
		infoDAO.insertInformation(RepresentationConverter.inverseTransformInformation(i));
	}
	
	public void updateInformation(InformationDTO i) {
		
		Information info = infoDAO.findById(i.getId());
		info.setText(i.getText());
		infoDAO.updateInformation(info);
	}
	

	public void deleteInformation(int id) {
		
		infoDAO.deleteInformation(id);
	}
}
