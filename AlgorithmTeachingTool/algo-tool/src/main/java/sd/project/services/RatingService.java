package sd.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dal.dao.InformationDAO;
import dal.dao.RatingDAO;
import dal.dao.UserDAO;
import dal.model.Information;
import dal.model.Rating;
import dal.model.User;
import sd.project.converters.RepresentationConverter;
import sd.project.dto.RatingDTO;

@Service
public class RatingService {

	private RatingDAO ratingDAO;
	
	@Autowired
	public RatingService() {
		
		this.ratingDAO = new RatingDAO();
	}
	
	public List<RatingDTO> getAllRatings() {
		
		List<Rating> allRatings  = ratingDAO.getAllRatings();
		List<RatingDTO> dtos = new ArrayList<RatingDTO>();
		for(Rating rating : allRatings) {
			
			dtos.add(RepresentationConverter.transformRating(rating));
		}
		return dtos;
	}
	
	public List<RatingDTO> getRatingsForInfo(int informationId) {
		
		List<Rating> allRatings  = ratingDAO.getAllRatings();
		List<RatingDTO> dtos = new ArrayList<RatingDTO>();
		for(Rating rating : allRatings) {
			
			if(rating.getInfo().getId()==informationId)
				dtos.add(RepresentationConverter.transformRating(rating));
		}
		return dtos;
	}
	
	public List<RatingDTO> getRatingsByUser(int userId) {
		
		List<Rating> allRatings  = ratingDAO.getAllRatings();
		List<RatingDTO> dtos = new ArrayList<RatingDTO>();
		for(Rating rating : allRatings) {
			
			if(rating.getUser().getId()==userId)
				dtos.add(RepresentationConverter.transformRating(rating));
		}
		return dtos;
	}
	
	public List<RatingDTO> getRatingsForUser(int userId) {
		
		List<Rating> allRatings  = ratingDAO.getAllRatings();
		List<RatingDTO> dtos = new ArrayList<RatingDTO>();
		for(Rating rating : allRatings) {
			
			if(rating.getInfo().getId()==userId)
				dtos.add(RepresentationConverter.transformRating(rating));
		}
		return dtos;
	}
	
	public boolean checkRated(RatingDTO rating) {
		
		InformationDAO infoDAO = new InformationDAO();
		Information info = infoDAO.findById(rating.getInformation().getId());
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByAnything("name", rating.getUser().getName());
		
		List<Rating> ratings = ratingDAO.getAllRatings();
		for(Rating r : ratings) {
			
			if(r.getInfo().getId() == info.getId()&&r.getUser().getId()==user.getId())
				return false;
		}
		return true;
	}
	public void addRating(RatingDTO rating) {
		
		//Step 1 ~ add the rating in the database
		InformationDAO infoDAO = new InformationDAO();
		Information info = infoDAO.findById(rating.getInformation().getId());
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByAnything("name", rating.getInformation().getAdmin().getName());
		
		Rating toBeAdded = new Rating();
		toBeAdded.setGrade(rating.getGrade());
		toBeAdded.setUser(userDAO.finById(rating.getUser().getId()));
		toBeAdded.setInfo(info);
		ratingDAO.insertRating(toBeAdded);
		
		//Step 2 ~ update information rating
		int rated = info.getRated() + 1;
		float newRating = (info.getRating()*info.getRated() + rating.getGrade())/rated;
		info.setRated(rated);
		info.setRating(newRating);
		infoDAO.updateInformation(info);
		
		//Step 3 ~ update user rating
		int ratedU = user.getRated() +1;
		float newRatingU = ((user.getRating()*user.getRated()) + rating.getGrade())/ratedU;
		user.setRated(ratedU);
		user.setRating(newRatingU);
		userDAO.updateUser(user);
	}
	
	public boolean alreadyRated(String userEmail, int informationId) {
		
		List<Rating> allRatings = ratingDAO.getAllRatings();
		for(Rating rating : allRatings) {
			
			if(rating.getUser().getEmail().equals(userEmail)&&rating.getInfo().getId()==informationId)
				return false;
		}
		
		return true;
	}
	
	public void updateRating(RatingDTO r) {
		
		ratingDAO.updateRating(RepresentationConverter.inverseTransformRating(r));
	}
	
	public void deleteRating(int id) {
		
		ratingDAO.deleteRating(id);
	}
}
