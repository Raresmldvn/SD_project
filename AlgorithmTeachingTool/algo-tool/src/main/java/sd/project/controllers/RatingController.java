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
import sd.project.dto.InformationDTO;
import sd.project.dto.RatingDTO;
import sd.project.dto.UserDTO;
import sd.project.forms.LogInForm;
import sd.project.forms.RatingForm;
import sd.project.services.AlgorithmService;
import sd.project.services.InformationService;
import sd.project.services.RatingService;
import sd.project.services.UserService;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InformationService infoService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/algorithm/{id}/information/{infoId}/{email:.+}")
	public String rateInformation(Model model, @ModelAttribute("ratingForm") RatingForm ratingForm, @PathVariable String id, @PathVariable String infoId,@PathVariable String email) {
		
		UserDTO user = userService.getUserByEmail(email);
		InformationDTO info = infoService.findById(Integer.parseInt(infoId));
		RatingDTO ratingDTO = new RatingDTO(0, user, info, Float.parseFloat(ratingForm.getGrade()));
		if(ratingService.checkRated(ratingDTO)) {
			ratingService.addRating(ratingDTO);
			return "redirect:/user/algorithm/" + id + "/information/" + infoId +"/" + email;
		} else {
			model.addAttribute("ratingForm", ratingForm);
			AlgorithmService algoService = new AlgorithmService();
			AlgorithmDTO algorithm = algoService .findById(Integer.parseInt(id));
			model.addAttribute("algoName", algorithm.getName());
			InformationDTO information = infoService.findById(Integer.parseInt(infoId));
			model.addAttribute("languageName", information.getLanguage().getName());
			model.addAttribute("userName", information.getAdmin().getName());
			model.addAttribute("rating", Float.toString(information.getRating()));
			model.addAttribute("information", information.getText());
			model.addAttribute("errorMessage", "You have already rated this information!");
			return "InformationPage";
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/ratings")
	public List<RatingDTO> getAllRatings() {
		return ratingService.getAllRatings();
	}
	
	@RequestMapping("/ratings/information/{id}")
	public List<RatingDTO> getByInformation(@PathVariable int id) {
		
		return ratingService.getRatingsForInfo(id);
	}
	
	@RequestMapping("/ratings/byusers/{id}")
	public List<RatingDTO> getByUser(@PathVariable int id) {
		
		return ratingService.getRatingsByUser(id);
	}
	
	@RequestMapping("/ratings/forusers/{id}")
	public List<RatingDTO> getForUsers(@PathVariable int id) {
		
		return ratingService.getRatingsForUser(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/ratings")
	public void addRating(@RequestBody RatingDTO r) {
		
		ratingService.addRating(r);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/ratings")
	public void updateRating(@RequestBody RatingDTO r) {
		
		ratingService.updateRating(r);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/ratings/{id}")
	public void deleteRating(@PathVariable int id) {
		
		ratingService.deleteRating(id);
	}
}
