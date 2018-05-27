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
import org.springframework.web.bind.annotation.RequestParam;

import sd.project.dto.AlgorithmDTO;
import sd.project.dto.LanguageDTO;
import sd.project.dto.RequestDTO;
import sd.project.dto.UserDTO;
import sd.project.forms.InformationForm;
import sd.project.services.AlgorithmService;
import sd.project.services.LanguageService;
import sd.project.services.RequestService;
import sd.project.services.UserService;
import sd.project.util.EmailService;
import sd.project.util.MailFactory;
import sd.project.util.MailObject;

@Controller
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private AlgorithmService algoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private EmailService emailService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/algorithm/{id}/{email:.+}")
	public String redirectToInfoFormOrPage(Model model, @RequestParam(value="action", required=true) String action,@PathVariable String id,  @PathVariable String email) {
		if(action.equals("Add-info"))
			return "redirect:/user/algorithm/" + id + "/infoform/" + email;
		else {
			String informationId;
			if("123456789".contains(action.substring(action.length()-2, action.length()-1)))
				informationId = action.substring(action.length()-2);
			else
				informationId = action.substring(action.length()-1);
			System.out.println(informationId);
			return "redirect:/user/algorithm/" + id + "/information/" + informationId +"/"+ email;
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/algorithm/{id}/infoform/{email:.+}")
	public String addRequest(Model model, @ModelAttribute("informationForm") InformationForm infoForm, @PathVariable String id, @PathVariable String email) {
			
		
		UserDTO user = userService.getUserByEmail(email);
		requestService.submitRequest(email, id, infoForm.getId(), infoForm.getText());
		model.addAttribute("informationForm",infoForm);
		List<LanguageDTO> languages = languageService.getAllLanguages();
		model.addAttribute("languages", languages);
		AlgorithmDTO algorithm =  algoService.findById(Integer.parseInt(id));
		model.addAttribute("algoName", algorithm.getName());
		
		String subject = "Request for " + algorithm.getName() + " from " + user.getEmail();
		String body = "You have a new request for algorithm " + algorithm.getName() + " from " + user.getName();
		MailObject mail = MailFactory.getMailObject("Normal", "raresmldvn31@gmail.com", "raresmldvn31@gmail.com", subject, body);
		emailService.sendSimpleMessage(mail);
		model.addAttribute("errorMessage", "A request to add this information has been sent to the admin!");
		return "InformationForm";
	} 
	
	@RequestMapping(method = RequestMethod.GET, value = "/requests")
	public List<RequestDTO> getAllRequests() {
		return requestService.getAllRequests();
	}
	
	@RequestMapping("/requests/{alg_name}")
	public List<RequestDTO> getByInformation(@PathVariable String alg_name) {
		
		return requestService.getAllRequestsForAlgorithm(alg_name);
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/requests")
	public void addRequest(@RequestBody RequestDTO r) {
		
		requestService.addRequest(r);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/requests")
	public void updateRequest(@RequestBody RequestDTO r) {
		
		requestService.updateRequest(r);;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/requests/{id}")
	public void deleteRequest(@PathVariable int id) {
		
		requestService.deleteRequest(id);
	}
}
