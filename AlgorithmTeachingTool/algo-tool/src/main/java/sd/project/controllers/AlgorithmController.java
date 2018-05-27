package sd.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sd.project.dto.AlgorithmDTO;
import sd.project.dto.DisciplineDTO;
import sd.project.dto.InformationDTO;
import sd.project.dto.LanguageDTO;
import sd.project.dto.UserDTO;
import sd.project.forms.AlgoToDisciplineForm;
import sd.project.forms.AlgorithmForm;
import sd.project.forms.RatingForm;
import sd.project.forms.UserForm;
import sd.project.services.AlgorithmService;
import sd.project.services.DisciplineService;
import sd.project.services.InformationService;
import sd.project.services.LanguageService;
import sd.project.services.UserService;

@Controller
public class AlgorithmController {

	@Autowired
	private AlgorithmService algorithmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private InformationService infoService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/admin/algoform/{email:.+}")
	public String displayAlgorithmAdminPage(Model model, @PathVariable String email) {
		
		 UserDTO admin = userService.getUserByEmail(email);
			if(admin!=null&&admin.isAdmin()==true) {
				List<AlgorithmDTO> algorithms = algorithmService.getAllAlgorithms();
				List<UserDTO> users = userService.getAllUsers();
				AlgorithmForm algorithmForm = new AlgorithmForm();
				model.addAttribute("algorithms", algorithms);
				model.addAttribute("algorithmForm", algorithmForm);
				model.addAttribute("users", users);
				DisciplineDTO disciplineForm = new DisciplineDTO();
				model.addAttribute("disciplineForm",disciplineForm);
				List<DisciplineDTO> disciplines = disciplineService.getAllDiscipline();
				model.addAttribute("disciplines", disciplines);
				List<LanguageDTO> languages = languageService.getAllLanguages();
				LanguageDTO languageForm = new LanguageDTO();
				model.addAttribute("languageForm", languageForm);
				model.addAttribute("languages", languages);
				AlgoToDisciplineForm algdisForm = new AlgoToDisciplineForm();
				model.addAttribute("algdisForm", algdisForm);
				return "AdminAlgorithm";
			}
			return "";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/admin/algoform/{email:.+}")
	public String manipulateAlgorithms(Model model, @ModelAttribute("languageForm") LanguageDTO languageForm, @ModelAttribute("disciplineForm") AlgoToDisciplineForm algDisForm, DisciplineDTO disciplineForm, @ModelAttribute("algorithmForm") AlgorithmForm algoForm, @ModelAttribute("algDisForm") @RequestParam(value="action", required=true) String action, @PathVariable String email) {
		if(action.equals("Add-algorithm")) {
			algorithmService.addAlgorithm(algoForm.getName(), Integer.parseInt(algoForm.getId()));
			}
			if(action.equals("Update-algorithm")) {
				algorithmService.updateAlgorithm(Integer.parseInt(algoForm.getAlgId()), algoForm.getName(),  Integer.parseInt(algoForm.getId()));
			}
			if(action.equals("Delete-algorithm")) {
				algorithmService.deleteAlgorithm(Integer.parseInt(algoForm.getAlgId()));
			}
			if(action.equals("Add-discipline")) {
				
				disciplineService.addDiscipline(disciplineForm);
			}
			if(action.equals("Update-discipline")) {
				
				disciplineService.updateDiscipline(disciplineForm);
			}
			if(action.equals("Delete-discipline")) {
	
				disciplineService.deleteDiscipline(disciplineForm.getId());
			}
			if(action.equals("Add-language")) {
				
				System.out.println("AICI");
				languageService.addLanguage(languageForm);
			}
			if(action.equals("Update-language")) {
				
				languageService.updateLanguage(languageForm);
			}
			if(action.equals("Delete-language")) {
				
				languageService.deleteLanguage(languageForm.getId());
			}
			if(action.equals("Add-relation")) {
				algorithmService.associateAlgorithmToDiscipline(Integer.parseInt(algDisForm.getAlgId()), Integer.parseInt(algDisForm.getDisId()));
			}
			return "redirect:/admin/algoform/" + email;
			
	}
	
	@RequestMapping(method = RequestMethod.GET,  value = "/user/algorithm/{id}/{email:.+}")
	public String displayAlgorithmPage(Model model,  @PathVariable String id, @PathVariable String email) {
		
		AlgorithmDTO algorithm = algorithmService.findById(Integer.parseInt(id));
		model.addAttribute("algoName", algorithm.getName());
		model.addAttribute("adminName", algorithm.getAdmin());
		List<InformationDTO> informations = infoService.getForAlgorithmValidated(algorithm.getName());
		InformationDTO descriptiveInfo = null;
		for(InformationDTO info : informations) {
			
			if(info.getLanguageName().equals("Description")) {
				
				descriptiveInfo = info;
				break;
			}
		}
		if(descriptiveInfo==null) {
			
			model.addAttribute("algorithmDescription", "No description available for this algorithm!");
		} else {
			
			model.addAttribute("algorithmDescription", descriptiveInfo.getText());
		}
		model.addAttribute("informations", informations);
		model.addAttribute("disciplines", algorithmService.getDisciplinesOfAlgorithm(Integer.parseInt(id)));
		return "AlgorithmPage";
	}
	
	@RequestMapping(method = RequestMethod.GET,  value = "/user/algorithm/{email:.+}")
	public String displayAlgorithmsPage(Model model, @PathVariable String email) {
		
		UserDTO user = userService.getUserByEmail(email);
		model.addAttribute("name", user.getName());
		RatingForm searchForm = new RatingForm();
		RatingForm disciplineForm = new RatingForm();
		RatingForm userForm = new RatingForm();
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("disciplineForm", disciplineForm);
		model.addAttribute("userForm", userForm);
		List<UserDTO> users = userService.getAllUsers();
		List<DisciplineDTO> disciplines = disciplineService.getAllDiscipline();
		List<AlgorithmDTO> algorithms = algorithmService.getAllAlgorithms();
		model.addAttribute("disciplines", disciplines);
		model.addAttribute("users", users);
		model.addAttribute("algorithms", algorithms);
		return "Algorithms";
	}
	
	@RequestMapping(method = RequestMethod.POST,  value = "/user/algorithm/{email:.+}")
	public String displayAlgorithmsPage(Model model, @ModelAttribute("searchForm") RatingForm searchForm, @ModelAttribute("disciplineForm") RatingForm disciplineForm, @ModelAttribute("userForm") RatingForm userForm, @RequestParam(value="action", required=true) String action, @PathVariable String email) {
		
		System.out.println(disciplineForm.getGrade() + " " + searchForm.getGrade() + " " + userForm.getGrade());
		UserDTO user = userService.getUserByEmail(email);
		model.addAttribute("name", user.getName());
		model.addAttribute("searchForm", new RatingForm());
		model.addAttribute("disciplineForm", new RatingForm());
		model.addAttribute("userForm", new RatingForm());
		List<UserDTO> users = userService.getAllUsers();
		List<DisciplineDTO> disciplines = disciplineService.getAllDiscipline();
		List<AlgorithmDTO> algorithms = new ArrayList<AlgorithmDTO>();
		if(action.equals("Search-all"))
			algorithms = algorithmService.getAllAlgorithms();
		if(action.equals("Search-name"))
			algorithms = algorithmService.getAlgorithmsForName(userForm.getGrade());
		if(action.equals("Search-discipline"))
			algorithms = algorithmService.getAlgorithmsForDiscipline(Integer.parseInt(disciplineForm.getGrade()));
		if(action.equals("Search-user")) {
			String name = userService.findById(Integer.parseInt(userForm.getGrade())).getName();
			System.out.println(name);
			algorithms = algorithmService.getAdministratedAlgorithms(name);
		}
		if(action.contains("Open")) {
			
			String indexOfAlgo = action.substring(action.length()-1);
			return "redirect:/user/algorithm/" + indexOfAlgo + "/" + email;
		}
		if(action.equals("Account")) {
			
			return "redirect:/users/" + email;
		}
		model.addAttribute("disciplines", disciplines);
		model.addAttribute("users", users);
		model.addAttribute("algorithms", algorithms);
		return "Algorithms";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/algorithms")
	public List<AlgorithmDTO> getAllAlgorithms() {
		return algorithmService.getAllAlgorithms();
	}
	
	@RequestMapping("/algorithms/{name}")
	public AlgorithmDTO getByName(@PathVariable String name) {
		
		return algorithmService.getAlgorithmByName(name);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/algorithms")
	public void addAlgorithm(@RequestBody AlgorithmDTO a) {
		
		algorithmService.addAlgorithm(a);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/algorithms")
	public void updateAlgorithm(@RequestBody AlgorithmDTO a) {
		
		algorithmService.updateAlgorithm(a);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/algorithms/{id}")
	public void deleteDiscipline(@PathVariable int id) {
		
		algorithmService.deleteAlgorithm(id);
	}
}
