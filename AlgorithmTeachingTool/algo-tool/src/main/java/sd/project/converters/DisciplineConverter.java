package sd.project.converters;

import dal.model.Discipline;
import sd.project.dto.DisciplineDTO;

public class DisciplineConverter {

	public DisciplineDTO transform(Discipline D) {
		
		DisciplineDTO dto = new DisciplineDTO(D.getId(), D.getName(), D.getDescription());
		return dto;
	}
	
	public Discipline inverseTransform(DisciplineDTO D) {
		
		Discipline d = new Discipline(D.getName(), D.getDescription());
		d.setId(D.getId());
		return d;
	}
}
