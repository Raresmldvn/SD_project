package dal.interfaces;

import java.util.ArrayList;

import dal.model.Information;


public interface InformationDAOInterface {

	public Information findByAnything(String column, String value);
	
	public Information findById(int id);
	
	public void insertInformation(Information toBeInserted);
	
	public void updateInformation(Information toBeUpdated);

	public void deleteInformation(int id);
	
	public ArrayList<Information> getAllInformation();
}
