package dal.interfaces;

import java.util.ArrayList;


import dal.model.Language;

public interface LanguageDAOInterface {

	public Language findByAnything(String column, String value);
	
	public Language findById(int id);
	
	public void insertLanguage(Language toBeInserted);
	
	public void updateLanguage(Language toBeUpdated);

	public void deleteLanguage(int id);
	
	public ArrayList<Language> getAllLanguages();
}
