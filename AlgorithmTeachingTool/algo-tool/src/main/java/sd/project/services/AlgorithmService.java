package sd.project.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dal.dao.AlgorithmDAO;
import dal.dao.DisciplineDAO;
import dal.dao.UserDAO;
import dal.model.Algorithm;
import dal.model.Discipline;
import dal.model.User;
import sd.project.converters.RepresentationConverter;
import sd.project.dto.AlgorithmDTO;
import sd.project.dto.DisciplineDTO;

@Service
public class AlgorithmService {

	private AlgorithmDAO algoDAO;
	private UserDAO userDAO;
	
	@Autowired
	public AlgorithmService() {
		
		this.algoDAO = new AlgorithmDAO();
		this.userDAO = new UserDAO();
	}
	
	public List<AlgorithmDTO> getAllAlgorithms() {
		
		List<Algorithm> all = algoDAO.getAllAlgorithms();
		List<AlgorithmDTO> dtos = new ArrayList<AlgorithmDTO>();
		for(Algorithm A : all) {
			
			dtos.add(RepresentationConverter.transformAlgorithm(A));
		}
		return dtos;
	}
	
	
	public AlgorithmDTO findById(int id) {
		
		return RepresentationConverter.transformAlgorithm(algoDAO.findById(id));
	}
	public List<AlgorithmDTO> getAdministratedAlgorithms(String name) {
		
		List<Algorithm> all = algoDAO.getAllAlgorithms();
		List<AlgorithmDTO> dtos = new ArrayList<AlgorithmDTO>();
		for(Algorithm A : all) {
			
			if(A.getAdmin().getName().equals(name))
				dtos.add(RepresentationConverter.transformAlgorithm(A));
		}
		return dtos;
	}
	
	public List<AlgorithmDTO> getAlgorithmsForDiscipline(int id) {
		
		DisciplineDAO disDAO = new DisciplineDAO();
		Discipline discipline = disDAO.findById(id);
		List<Algorithm> all = algoDAO.getAllAlgorithms();
		List<AlgorithmDTO> dtos = new ArrayList<AlgorithmDTO>();
		for(Algorithm A : all) {
			
			Set<Discipline> disSet = A.getDisciplines();
			if(this.containsDiscipline(disSet, discipline))
				dtos.add(RepresentationConverter.transformAlgorithm(A));
		}
		return dtos;
	}
	
	private boolean containsDiscipline(Set<Discipline> set, Discipline d) {
		
		for(Discipline dis : set) {
			
			if(dis.getId()==d.getId())
				return true;
		}
		return false;
	}
	
	public List<AlgorithmDTO> getAlgorithmsForName(String name) {
		
		List<Algorithm> all = algoDAO.getAllAlgorithms();
		List<AlgorithmDTO> dtos = new ArrayList<AlgorithmDTO>();
		for(Algorithm A : all) {
			
			if(A.getName().contains(name))
				dtos.add(RepresentationConverter.transformAlgorithm(A));
		}
		return dtos;
	}
	
	public boolean relateDisciplineToAlgorithm(int algId, int disId) {
		
		DisciplineDAO disDAO = new DisciplineDAO();
		Algorithm algorithm = algoDAO.findById(algId);
		Discipline discipline = disDAO.findById(disId);
		Set<Discipline> set = algorithm.getDisciplines();
		boolean ret = containsDiscipline(set, discipline);
		set.add(discipline);
		algorithm.setDisciplines(set);
		if(ret==false)
			algoDAO.updateAlgorithm(algorithm);
		return ret;
	}

	public List<DisciplineDTO> getDisciplinesOfAlgorithm(int id) {
		
		Algorithm algorithm = algoDAO.findById(id);
		Set<Discipline> set = algorithm.getDisciplines();
		List<DisciplineDTO> returnedList = new ArrayList<DisciplineDTO>();
		for(Discipline discipline: set) {
			
			returnedList.add(RepresentationConverter.transformDiscipline(discipline));
		}
		return returnedList;
	}
	public AlgorithmDTO getAlgorithmByName(String name) {
		
		Algorithm A = algoDAO.findByAnything("name", name);
		return RepresentationConverter.transformAlgorithm(A);
	}
	
	public void addAlgorithm(String name, int userId) {
		
		User user = userDAO.finById(userId);
		Algorithm algorithm = new Algorithm();
		algorithm.setName(name);
		algorithm.setAdmin(user);
		algoDAO.insertAlgorithm(algorithm);
	}
	
	public void updateAlgorithm(int id, String name, int userId) {
		
		User user = userDAO.finById(userId);
		Algorithm algorithm = algoDAO.findById(id);
		algorithm.setName(name);
		algorithm.setAdmin(user);
		algoDAO.updateAlgorithm(algorithm);
	}
	public void addAlgorithm(AlgorithmDTO algorithm) {
		
		algoDAO.insertAlgorithm(RepresentationConverter.inverseTransformAlgorithm(algorithm));
	}
	
	public void updateAlgorithm(AlgorithmDTO algorithm) {
		
	    Algorithm algo = algoDAO.findByAnything("name", algorithm.getName());
	    Set<Discipline> updatedDisciplines = new HashSet<Discipline>();
	    for(DisciplineDTO D : algorithm.getDisciplines()) {
	    	
	    	Discipline dis = (new DisciplineDAO()).findByAnything("name", D.getName());
	    	updatedDisciplines.add(dis);
	    }
	    algo.setDisciplines(updatedDisciplines);
	    algoDAO.updateAlgorithm(algo);
	}
	
	public void associateAlgorithmToDiscipline(int algoId, int disId) {
		
		Algorithm algorithm = algoDAO.findById(algoId);
		Discipline discipline = (new DisciplineDAO()).findById(disId);
		Set<Discipline> newDisciplines = algorithm.getDisciplines();
		newDisciplines.add(discipline);
		for(Discipline d : newDisciplines) {
			
			System.out.println(d.getId() + d.getName());
		}
		for(Discipline d: algorithm.getDisciplines()) {
			
			System.out.println(d.getId() + d.getName());
		}
		algoDAO.updateAlgorithm(algorithm);
	}
	
	public void deleteAlgorithm(int id) {
		
		algoDAO.deleteAlgorithm(id);
	}
}
