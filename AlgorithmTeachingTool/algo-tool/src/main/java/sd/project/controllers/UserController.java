package sd.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sd.project.dto.AlgorithmDTO;
import sd.project.dto.InformationDTO;
import sd.project.dto.RequestDTO;
import sd.project.dto.UserDTO;
import sd.project.forms.AlgorithmForm;
import sd.project.forms.LogInForm;
import sd.project.forms.RatingForm;
import sd.project.forms.UserForm;
import sd.project.services.AlgorithmService;
import sd.project.services.InformationService;
import sd.project.services.RequestService;
import sd.project.services.UserService;
import sd.project.util.ReportGenerator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AlgorithmService algorithmService;
	
	@Autowired
	private InformationService infoService;
	
	@Autowired
	private RequestService requestService;
	
	@Value("${errorMessage1}")
	private String errorMessage1;
	
	@Value("${errorMessage2}")
	private String errorMessage2;
	
	@RequestMapping(value = "/logIn", method = RequestMethod.GET)
	public String showLogInPage(Model model) {
		
		LogInForm logInForm = new LogInForm();
		model.addAttribute("logInForm", logInForm);
		
		return "logIn";
	}
	
	@RequestMapping(value = "/logIn", method = RequestMethod.POST) 
	public String logIn(Model model, @ModelAttribute("logInForm") LogInForm logInForm) {
		
		String email = logInForm.getEmail();
		String password = logInForm.getPassword();
		UserDTO User = userService.getUserByEmail(email);
		if(User!=null&&User.getPassword().equals(password)) {
			if(User.isAdmin()==true)
				return "redirect:/admin/" + User.getEmail();
			else
				return "redirect:/user/algorithm/" + User.getEmail();
		}
		else {
			if(User==null)
				model.addAttribute("errorMessage", errorMessage1);
			else
				model.addAttribute("errorMessage", errorMessage2);
			return "logIn";
		}
		
	}
	
	@RequestMapping(value = "/admin/{email:.+}", method = RequestMethod.GET)
	public String showAdminPage(Model model, @PathVariable String email) {
		
		UserDTO user = userService.getUserByEmail(email);
		if(user.isAdmin()==true) {
			
			model.addAttribute("name", user.getName());
			return "Administrator";
		}
		return "";
	}
	
	@RequestMapping(value = "/admin/{email:.+}", method = RequestMethod.POST)
	public String redirecToAdminChoice(Model model,  @RequestParam(value="action", required=true) String action, @PathVariable String email) {
		
		UserDTO user = userService.getUserByEmail(email);
		if(user.isAdmin()==true) {
			
			if(action.equals("Algorithms")) {
				
				return "redirect:/admin/algoform/" + email;
			}
			if(action.equals("Users")) {
				
				return "redirect:/admin/userform/" + email;
			}
			if(action.equals("Visit")) {
				
				return "redirect:/user/algorithm/" + email;
			}
			if(action.equals("RatingUsers")) {
				
				ReportGenerator.createUserReport();
				return "redirect:/admin/" + email;
			}
			if(action.equals("RatingInfos")) {
				
				ReportGenerator.createInfoReport();
				return "redirect:/admin/" + email;
			}
		}
		return "";
	}
	
	
	@RequestMapping(value = "admin/userform/{email:.+}", method = RequestMethod.GET)
	public String showUserFormPage(Model model, @PathVariable String email) {
		
	    UserDTO admin = userService.getUserByEmail(email);
		if(admin!=null&&admin.isAdmin()==true) {
			List<UserDTO> users = userService.getAllUsers();
			UserForm userForm = new UserForm();
			model.addAttribute("userForm", userForm);
			model.addAttribute("users", users);
			return "User";
		}
		return "";
	}
	
	
	@RequestMapping(value = "/users/{email:.+}", method = RequestMethod.GET) 
	public String showUserPage(Model model, @PathVariable String email) {
		
		UserDTO user = userService.getUserByEmail(email);
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("rating", user.getRating());
		model.addAttribute("rated", user.getRated());
		List<AlgorithmDTO> algorithms = algorithmService.getAdministratedAlgorithms(user.getName());
		model.addAttribute("algorithms", algorithms);
		List<InformationDTO> infos = infoService.getForUser(user.getName());
		model.addAttribute("informations", infos);
		List<RequestDTO> request = (new RequestService()).getAllRequestsForUserValidate(user.getName());
		model.addAttribute("requests", request);
		return "UserPage";
	}
	
	@RequestMapping(value = "/users/{email:.+}", method = RequestMethod.POST) 
	public String openOrAccept(Model model, @RequestParam(value="action", required=true) String action, @PathVariable String email) {
		
		String index;
		if(("123456789").contains(action.substring(action.length()-2, action.length()-1)))
			index = action.substring(action.length()-2);
		else
			index = action.substring(action.length()-1);
		System.out.println(action.substring(action.length()-2, action.length()-1));
		if(action.contains("Accept")) {
			
			infoService.validate(index);
			return "redirect:/users/" + email;
		} else {
			
			if(action.contains("Reject")) {
				
				infoService.reject(index);
				return "redirect:/users/" + email;
			} else {
			System.out.println(index);
			int infoIndex = requestService.findById(Integer.parseInt(index)).getId();
			String algorithmName = action.substring(0, action.length()-2);
			int algId = algorithmService.getAlgorithmByName(algorithmName).getId();
			return "redirect:/user/algorithm/" + Integer.toString(algId) + "/information/" + infoIndex + "/" + email;
			}
		}
	}
	@RequestMapping( value="/admin/userform/{eemail:.+}", method=RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("userForm") UserForm userForm, @RequestParam(value="action", required=true) String action, @PathVariable String eemail) {
		
		if(action.equals("Add")) {
		String name = userForm.getName();
		String email = userForm.getEmail();
		String password = userForm.getPassword();
		UserDTO user = new UserDTO(1, name, email, password, false, 0.0f, 0);
		userService.addUser(user);
		}
		if(action.equals("Update")) {
			
			int id = Integer.parseInt(userForm.getId());
			String name = userForm.getName();
			String email = userForm.getEmail();
			String password = userForm.getPassword();
			UserDTO user = new UserDTO(id, name, email, password, false, 0.0f, 0);
			userService.updateUser(user);
		}
		if(action.equals("Delete")) {
			
			int id = Integer.parseInt(userForm.getId());
			userService.deleteUser(id);
		}
		return "redirect:/admin/userform/" + eemail;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping("/users/{email:.+}")
	public UserDTO getByEmail(@PathVariable String email) {
		
		return userService.getUserByEmail(email);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public void addUser(@RequestBody UserDTO u) {
		
		userService.addUser(u);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/users")
	public void updateUser(@RequestBody UserDTO u) {
		
		userService.updateUser(u);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		userService.deleteUser(id);
	}
	
}
