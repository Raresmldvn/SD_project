package sd.project.converters;

import java.util.HashSet;
import java.util.Set;

import dal.model.Algorithm;
import dal.model.Discipline;
import sd.project.dto.AlgorithmDTO;
import sd.project.dto.DisciplineDTO;

public class AlgorithmConverter {

	private UserConverter userConverter = new UserConverter();
	private DisciplineConverter disConverter = new DisciplineConverter();
	public AlgorithmDTO transform(Algorithm A) {
		if(A==null)
			return null;
		Set<DisciplineDTO> setDTOs = new HashSet<DisciplineDTO>();
		for(Discipline D : A.getDisciplines()) {
			
			setDTOs.add(disConverter.transform(D));
		}
		AlgorithmDTO dto = new AlgorithmDTO(A.getId(), A.getName(),userConverter.transform(A.getAdmin()),setDTOs);
		return dto;
	}
	
	public Algorithm inverseTransform(AlgorithmDTO A) {
		

		Set<Discipline> disciplines = new HashSet<Discipline>();
		for(DisciplineDTO D : A.getDisciplines()) {
			
			disciplines.add(disConverter.inverseTransform(D));
		}
		Algorithm alg = new Algorithm();
		alg.setDisciplines(disciplines);
		alg.setAdmin(userConverter.inverseTransform(A.getUser()));
		alg.setId(A.getId());
		alg.setName(A.getName());
		return alg;
	}
}
