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
		systemLogger.setAction("user logged in");
		systemLogger.setDetails("email: " + Main.current_user.getEmail().toLowerCase());
		addLog(systemLogger);
	}
	
	pointcut movieAddedCall():
		call(void controller.impl.ManageProductControllerImpl.SubmitListener.updateNotice());
	
	after(): movieAddedCall(){
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("new movie added");
		systemLogger.setDetails("title: " + " type: ");
		addLog(systemLogger);
	}
	
	pointcut movieEditedCall(String title):
		call(void controller.impl.ManageProductControllerImpl.EditListener.updateNotice(String))
		&& args(title);
	
	after(String title) returning: movieEditedCall(title){
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("movie modified");
		systemLogger.setDetails("title: " + title.toLowerCase() + " type: ");
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
