package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.LanguageDAOInterface;
import dal.model.Language;
import dal.util.HibernateAnnotationUtil;

public class LanguageDAO implements LanguageDAOInterface{

	public Language findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Language where " + column + "=:value");
		query.setString("value", value);
		Language language = (Language)query.uniqueResult();
		transaction.commit();
		return language;
	}
	
	public Language findById(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Language where id=:value");
		query.setInteger("value", id);
		Language language = (Language)query.uniqueResult();
		transaction.commit();
		return language;
	}
	
	public void insertLanguage(Language toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateLanguage(Language toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteLanguage(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from Language where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<Language> getAllLanguages() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from Language");
		@SuppressWarnings("unchecked")
		List<Language> languages = query.list();
		ArrayList<Language> finalList = new ArrayList<Language>();
		for(Language P : languages) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}
	
	public static void main(String args[]) {
	
		LanguageDAO dao = new LanguageDAO();
		ArrayList<Language> all = dao.getAllLanguages();
		System.out.println(all.get(0).getName());
	}
}
