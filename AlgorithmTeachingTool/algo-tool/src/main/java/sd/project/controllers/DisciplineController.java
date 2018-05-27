package sd.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sd.project.dto.DisciplineDTO;
import sd.project.services.DisciplineService;

@RestController
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/disciplines")
	public List<DisciplineDTO> getAllDisciplines() {
		return disciplineService.getAllDiscipline();
	}
	
	@RequestMapping("/disciplines/{name}")
	public DisciplineDTO getByEmail(@PathVariable String name) {
		
		return disciplineService.findByName(name);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/disciplines")
	public void addDiscipline(@RequestBody DisciplineDTO d) {
		
		disciplineService.addDiscipline(d);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/disciplines")
	public void updateDiscipline(@RequestBody DisciplineDTO d) {
		
		disciplineService.updateDiscipline(d);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/disciplines/{id}")
	public void deleteDiscipline(@PathVariable int id) {
		
		disciplineService.deleteDiscipline(id);
	}
}
