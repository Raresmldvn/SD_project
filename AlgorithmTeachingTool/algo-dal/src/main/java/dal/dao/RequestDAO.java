package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.RequestDAOInterface;
import dal.model.Discipline;
import dal.model.Information;
import dal.model.Request;
import dal.util.HibernateAnnotationUtil;

public class RequestDAO implements RequestDAOInterface{

	public Request findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Request where " + column + "=:value");
		query.setString("value", value);
		Request req = (Request)query.uniqueResult();
		transaction.commit();
		return req;
	}
	
	public Request findById(int id) {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Request where id=:value");
		query.setInteger("value", id);
		Request dis = (Request)query.uniqueResult();
		transaction.commit();
		return dis;
	}
	
	public void insertRequest(Request toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateRequest(Request toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteRequest(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from Information where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<Request> getAllRequests() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from Request");
		@SuppressWarnings("unchecked")
		List<Request> reqs = query.list();
		ArrayList<Request> finalList = new ArrayList<Request>();
		for(Request P : reqs) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}
}
