package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.UserDAOInterface;
import dal.model.User;
import dal.util.HibernateAnnotationUtil;

public class UserDAO implements UserDAOInterface{

	public User finById(int id) {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from User where id=:value");
		query.setInteger("value", id);
		User user = (User)query.uniqueResult();
		transaction.commit();
		return user;
	}
	public User findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from User where " + column + "=:value");
		query.setString("value", value);
		User user = (User)query.uniqueResult();
		transaction.commit();
		return user;
	}
	
	public void insertUser(User toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateUser(User toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteUser(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from User where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<User> getAllUsers() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		ArrayList<User> finalList = new ArrayList<User>();
		for(User P : users) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}

}
