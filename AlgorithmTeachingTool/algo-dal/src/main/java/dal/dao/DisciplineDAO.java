package dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.interfaces.DisciplineDAOInterface;
import dal.model.Algorithm;
import dal.model.Discipline;
import dal.util.HibernateAnnotationUtil;

public class DisciplineDAO implements DisciplineDAOInterface{

	public Discipline findByAnything(String column, String value) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Discipline where " + column + "=:value");
		query.setString("value", value);
		Discipline discipline = (Discipline)query.uniqueResult();
		transaction.commit();
		return discipline;
	}
	
	public Discipline findById(int id) {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Discipline where id=:value");
		query.setInteger("value", id);
		Discipline dis = (Discipline)query.uniqueResult();
		transaction.commit();
		return dis;
	}
	
	public void insertDiscipline(Discipline toBeInserted) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.save(toBeInserted);
		transaction.commit();
	}
	
	public void updateDiscipline(Discipline toBeUpdated) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(toBeUpdated);
		transaction.commit();
	}

	public void deleteDiscipline(int id) {
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();		
		currentSession.createQuery("delete from Discipline where id=:idParameter").setLong("idParameter", id).executeUpdate();
		transaction.commit();
		
	}
	
	public ArrayList<Discipline> getAllDisciplines() {
		
		Session currentSession = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();	
		Query query = currentSession.createQuery("from Discipline");
		@SuppressWarnings("unchecked")
		List<Discipline> disciplines = query.list();
		ArrayList<Discipline> finalList = new ArrayList<Discipline>();
		for(Discipline P : disciplines) {
				
			finalList.add(P);
		}
		transaction.commit();
		return finalList;
	}
	
}
