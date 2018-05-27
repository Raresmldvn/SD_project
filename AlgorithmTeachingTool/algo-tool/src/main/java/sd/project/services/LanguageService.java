package sd.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dal.dao.LanguageDAO;
import dal.model.Language;
import sd.project.dto.LanguageDTO;

@Service
public class LanguageService {

	private LanguageDAO languageDAO;
	
	@Autowired
	public LanguageService() {
		
		languageDAO = new LanguageDAO();
	}
	
	public List<LanguageDTO> getAllLanguages() {
		
		List<Language> all = languageDAO.getAllLanguages();
		List<LanguageDTO> allDTOS = new ArrayList<LanguageDTO>();
		
		for(Language l : all) {
			
			allDTOS.add(transform(l));
		}
		return allDTOS;
	}
	
	public LanguageDTO getLanguageById(int id) {
		
		return transform(languageDAO.findById(id));
	}
	
	public void addLanguage(LanguageDTO language) {
		
		languageDAO.insertLanguage(inverseTransform(language));
	}
	
	public void updateLanguage(LanguageDTO language) {
		
		Language l = languageDAO.findById(language.getId());
		l.setName(language.getName());
		languageDAO.updateLanguage(l);
	}
	
	public void deleteLanguage(int id) {
		
		languageDAO.deleteLanguage(id);
	}
	
	private LanguageDTO transform(Language l) {
		
		LanguageDTO dto = new LanguageDTO(l.getId(), l.getName());
		return dto;
	}
	
	private Language inverseTransform(LanguageDTO l) {
		
		Language ent = new Language(l.getName());
		ent.setId(l.getId());
		return ent;
	}
}
