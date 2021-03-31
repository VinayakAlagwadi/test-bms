package com.ltts.project.Dao;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ltts.project.model.Movie;
import com.ltts.project.model.Screen;


@Repository
public class ScreenDao {

//	private EntityManager em;
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private SessionFactory sf;
	
	public boolean InsertScreen(Screen Sc) {
		boolean b=false;
		Session s=null;
		try {
			s=sf.openSession();
			s.beginTransaction();
			
			s.save(Sc);
			//System.out.println(st);
			s.getTransaction().commit();
			
		}
		catch(Exception e) {
			System.out.println("Exception "+e);
			b=true;
		}
		/*
		 * finally { sf.close(); }
		 */
		
		return b;
	}
	
	public Screen getScreenByMid(int id){
		 Session session=sf.openSession();
		
			
			  Query query = session.createQuery("from Screen where id= :id");
			  query.setLong("id", id);
			  Screen s =  (Screen) query.uniqueResult();
			  
			  return s;
			 		
		
	}
	
	public void UpdateSeats(int id,int b){
		
	 Session session=sf.openSession();
	 Transaction tx=session.beginTransaction();  
	 
	 Query query = session.createQuery("update Screen set noOfSeats=:n where id=:id");  
	 query.setLong("id", id); 
	 
	 Screen s = new Screen();
	// int seats  =s.getNoOfSeats();
	 query.setParameter("n",b);  
	
	 int st=query.executeUpdate();  
	System.out.println(st);
	tx.commit();
	
	}
}




