package dal.interfaces;

import java.util.ArrayList;

import dal.model.Rating;

public interface RatingDAOInterface {

	public Rating findByAnything(String column, String value);
	
	public void insertRating(Rating toBeInserted);
	
	public void updateRating(Rating toBeUpdated);

	public void deleteRating(int id);
	
	public ArrayList<Rating> getAllRatings();
}
