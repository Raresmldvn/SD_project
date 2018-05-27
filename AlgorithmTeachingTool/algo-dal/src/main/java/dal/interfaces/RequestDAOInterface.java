package dal.interfaces;

import java.util.ArrayList;

import dal.model.Request;


public interface RequestDAOInterface {

	public Request findByAnything(String column, String value);
	
	public Request findById(int id);
	
	public void insertRequest(Request toBeInserted);
	
	public void updateRequest(Request toBeUpdated);

	public void deleteRequest(int id);
	
	public ArrayList<Request> getAllRequests();
}
