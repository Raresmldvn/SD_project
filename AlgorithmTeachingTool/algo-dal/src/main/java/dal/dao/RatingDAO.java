package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.RatingDAOInterface;
import dal.model.Rating;
import dal.util.HibernateAnnotationUtil;

public class RatingDAO implements RatingDAOInterface {

	public Rating findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Rating where " + column + "=:value");
		query.setString("value", value);
		Rating rating = (Rating)query.uniqueResult();
		transaction.commit();
		return rating;
	}
	
	public void insertRating(Rating toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateRating(Rating toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteRating(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from Rating where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<Rating> getAllRatings() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from Rating");
		@SuppressWarnings("unchecked")
		List<Rating> ratings = query.list();
		ArrayList<Rating> finalList = new ArrayList<Rating>();
		for(Rating P : ratings) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}
}
