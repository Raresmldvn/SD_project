package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.InformationDAOInterface;
import dal.model.Information;
import dal.model.Language;
import dal.util.HibernateAnnotationUtil;

public class InformationDAO implements InformationDAOInterface {

	public Information findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Information where " + column + "=:value");
		query.setString("value", value);
		Information alg = (Information)query.uniqueResult();
		transaction.commit();
		return alg;
	}
	
	public Information findById(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Information where id=:value");
		query.setInteger("value", id);
		Information information = (Information)query.uniqueResult();
		transaction.commit();
		return information;
	}
	public void insertInformation(Information toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateInformation(Information toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteInformation(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from Information where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<Information> getAllInformation() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from Information");
		@SuppressWarnings("unchecked")
		List<Information> infos = query.list();
		ArrayList<Information> finalList = new ArrayList<Information>();
		for(Information P : infos) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}
}
