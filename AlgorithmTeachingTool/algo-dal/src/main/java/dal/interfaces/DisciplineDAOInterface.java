package dal.interfaces;

import java.util.ArrayList;

import dal.model.Discipline;

public interface DisciplineDAOInterface {
	public Discipline findByAnything(String column, String value);
	
	public Discipline findById(int id);
	
	public void insertDiscipline(Discipline toBeInserted);
	
	public void updateDiscipline(Discipline toBeUpdated);

	public void deleteDiscipline(int id);
	
	public ArrayList<Discipline> getAllDisciplines();
}
