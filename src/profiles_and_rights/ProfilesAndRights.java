package profiles_and_rights;

import javax.persistence.TypedQuery;

import dao.impl.DaoImpl;
import model.Profile;
import model.Right;

public class ProfilesAndRights {
	protected DaoImpl dao;
	
	public ProfilesAndRights(){
		try {
			TypedQuery<Profile> q2 = dao.getEntityManager().createQuery("SELECT p FROM Profile p", Profile.class);
			System.out.println("Inside try");
		}
		catch (NullPointerException e) {
			System.out.println("Inside catch");
			Profile p2 = new Profile("employee");
			Profile p3 = new Profile("customer");
			
			Right r1 = new Right("show_product");
			Right r2 = new Right("manage_product");
			Right r3 = new Right("manage_customer");
			
			p2.grantRight(r1);
			p2.grantRight(r2);
			p2.grantRight(r3);
			
			p3.grantRight(r1);
			
		    dao.getEntityManager().persist(p2);
		    dao.getEntityManager().persist(p3);
	        
		    dao.getEntityManager().persist(r1);
		    dao.getEntityManager().persist(r2);
		    dao.getEntityManager().persist(r3);
		}
	}
	
}
