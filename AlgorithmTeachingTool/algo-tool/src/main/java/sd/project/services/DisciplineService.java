package sd.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dal.dao.DisciplineDAO;
import dal.model.Discipline;
import sd.project.dto.DisciplineDTO;

@Service
public class DisciplineService {

	private DisciplineDAO disDAO;
	
	@Autowired
	public DisciplineService() {
		
		disDAO = new DisciplineDAO();
	}
	
	public List<DisciplineDTO> getAllDiscipline() {
		
		List<DisciplineDTO> toBeReturned = new ArrayList<DisciplineDTO>();
		List<Discipline> allDisciplines = disDAO.getAllDisciplines();
		for(Discipline d : allDisciplines) {
			
			toBeReturned.add(transform(d));
		}
		return toBeReturned;
	}
	
	public DisciplineDTO findByName(String name) {
		
		return transform(disDAO.findByAnything("name", name));
	}
	
	public void addDiscipline(DisciplineDTO toBeAdded) {
		
		disDAO.insertDiscipline(inverseTransform(toBeAdded));
	}
	
	public void updateDiscipline(DisciplineDTO toBeUpdated) {
		
		Discipline D = disDAO.findByAnything("name", toBeUpdated.getName());
		D.setDescription(toBeUpdated.getDescription());
		disDAO.updateDiscipline(D);
	}
	
	public void deleteDiscipline(int id) {
		
		disDAO.deleteDiscipline(id);
	}
	
	
	private DisciplineDTO transform(Discipline D) {
		
		DisciplineDTO dto = new DisciplineDTO(D.getId(), D.getName(), D.getDescription());
		return dto;
	}
	
	private Discipline inverseTransform(DisciplineDTO D) {
		
		Discipline d = new Discipline(D.getName(), D.getDescription());
		d.setId(D.getId());
		return d;
	}
}
