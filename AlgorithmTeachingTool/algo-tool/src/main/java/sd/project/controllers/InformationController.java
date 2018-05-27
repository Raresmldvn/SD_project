package sd.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sd.project.dto.AlgorithmDTO;
import sd.project.dto.DisciplineDTO;
import sd.project.dto.InformationDTO;
import sd.project.dto.LanguageDTO;
import sd.project.forms.InformationForm;
import sd.project.forms.LogInForm;
import sd.project.forms.RatingForm;
import sd.project.services.AlgorithmService;
import sd.project.services.InformationService;
import sd.project.services.LanguageService;
import sd.project.services.UserService;

@Controller
public class InformationController {

	@Autowired
	private InformationService infoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AlgorithmService algoService;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/algorithm/{id}/information/{infoId}/{email:.+}")
	public String showInformationPage(Model model, @PathVariable String id, @PathVariable String infoId, @PathVariable String email) {
		
		RatingForm ratingForm = new RatingForm();
		model.addAttribute("ratingForm", ratingForm);
		AlgorithmDTO algorithm = algoService.findById(Integer.parseInt(id));
		model.addAttribute("algoName", algorithm.getName());
		InformationDTO information = infoService.findById(Integer.parseInt(infoId));
		model.addAttribute("languageName", information.getLanguage().getName());
		model.addAttribute("userName", information.getAdmin().getName());
		model.addAttribute("rating", Float.toString(information.getRating()));
		model.addAttribute("information", information.getText());
		return "InformationPage";
	}
	
	/*@RequestMapping(method = RequestMethod.POST, value = "/open")
	public String redirectToInfoPage()*/
	@RequestMapping(method = RequestMethod.GET, value = "/user/algorithm/{id}/infoform/{email:.+}")
	public String displayFormPage(Model model, @PathVariable String id, @PathVariable String email) {
		
		InformationForm infoForm = new InformationForm();
		model.addAttribute("informationForm",infoForm);
		List<LanguageDTO> languages = languageService.getAllLanguages();
		model.addAttribute("languages", languages);
		AlgorithmDTO algorithm =  algoService.findById(Integer.parseInt(id));
		model.addAttribute("algoName", algorithm.getName());
		return "InformationForm";
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/information")
	public List<InformationDTO> getAllInformation() {
		return infoService.getAllInformation();
	}
	
	@RequestMapping("/information/algorithm/{name}")
	public List<InformationDTO> getForAlgorithm(@PathVariable String name) {
		
		return infoService.getForAlgorithm(name);
	}
	
	@RequestMapping("/information/user/{name}")
	public List<InformationDTO> getForUser(@PathVariable String name) {
		
		return infoService.getForUser(name);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/information")
	public void addInformation(@RequestBody InformationDTO i) {
		
		infoService.addInformation(i);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/information")
	public void updateInformation(@RequestBody InformationDTO i) {
		
		infoService.updateInformation(i);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/information/{id}")
	public void deleteInformation(@PathVariable int id) {
		
		infoService.deleteInformation(id);
	}
}
