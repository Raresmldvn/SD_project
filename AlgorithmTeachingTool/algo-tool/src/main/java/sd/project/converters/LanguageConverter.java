package sd.project.converters;

import dal.model.Language;
import sd.project.dto.LanguageDTO;

public class LanguageConverter {

	public LanguageDTO transform(Language l) {
		
		LanguageDTO dto = new LanguageDTO(l.getId(), l.getName());
		return dto;
	}
	
	public Language inverseTransform(LanguageDTO l) {
		
		Language ent = new Language(l.getName());
		ent.setId(l.getId());
		return ent;
	}
}
