package sd.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sd.project.dto.LanguageDTO;
import sd.project.services.LanguageService;

@RestController
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/languages")
	public List<LanguageDTO> getAllLanguages() {
		
		return languageService.getAllLanguages();
	}
	
	@RequestMapping("/languages/{id}")
	public LanguageDTO getById(@PathVariable int id) {
		
		return languageService.getLanguageById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/languages")
	public void addLanguage(@RequestBody LanguageDTO language) {
		
		languageService.addLanguage(language);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/languages")
	public void updateUser(@RequestBody LanguageDTO language) {
		
		languageService.updateLanguage(language);;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/languages/{id}")
	public void deleteUser(@PathVariable int id) {
		
		languageService.deleteLanguage(id);
	}
}
