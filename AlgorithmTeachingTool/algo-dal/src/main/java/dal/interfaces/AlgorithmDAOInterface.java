package dal.interfaces;

import java.util.ArrayList;

import dal.model.Algorithm;


public interface AlgorithmDAOInterface {

	public Algorithm findById(int id);
	
	public Algorithm findByAnything(String column, String value);
	
	public void insertAlgorithm(Algorithm toBeInserted);
	
	public void updateAlgorithm(Algorithm toBeUpdated);

	public void deleteAlgorithm(int id);

	public ArrayList<Algorithm> getAllAlgorithms();
}
