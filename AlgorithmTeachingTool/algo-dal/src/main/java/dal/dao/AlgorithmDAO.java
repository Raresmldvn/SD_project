package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.AlgorithmDAOInterface;
import dal.model.Algorithm;
import dal.util.HibernateAnnotationUtil;

public class AlgorithmDAO implements AlgorithmDAOInterface{

	public Algorithm findById(int id) {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Algorithm where id=:value");
		query.setInteger("value", id);
		Algorithm alg = (Algorithm)query.uniqueResult();
		transaction.commit();
		return alg;
	}
	public Algorithm findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Algorithm where " + column + "=:value");
		query.setString("value", value);
		Algorithm alg = (Algorithm)query.uniqueResult();
		transaction.commit();
		return alg;
	}
	
	public void insertAlgorithm(Algorithm toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateAlgorithm(Algorithm toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteAlgorithm(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from Algorithm where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<Algorithm> getAllAlgorithms() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from Algorithm");
		@SuppressWarnings("unchecked")
		List<Algorithm> algorithms = query.list();
		ArrayList<Algorithm> finalList = new ArrayList<Algorithm>();
		for(Algorithm P : algorithms) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}
	
}
