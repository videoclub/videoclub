package sysaspect;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.Main;
import model.SystemLogger;
import controller.PersistenceController;
import dao.SystemLoggerDao;
import dao.impl.SystemLoggerDaoImpl;

public aspect SystemLog {
	
	
	pointcut userLoggedInCall():
		call(void controller.LoginController.userLoggedIn());
	
	after(): userLoggedInCall(){
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("User Logged In");
		systemLogger.setDetails("Email: " + Main.current_user.getEmail());
		addLog(systemLogger);
	}
	
	
	
	
	
	public void addLog(SystemLogger systemLogger){
		systemLoggerDao.persist(systemLogger);
	}
	
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private SystemLoggerDao systemLoggerDao = new SystemLoggerDaoImpl(em);
	private SystemLogger systemLogger;
	private Date now;
}
