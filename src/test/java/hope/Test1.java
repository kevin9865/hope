package hope;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.hope.systemManager.approveManager.dao.ApproveContentHeaderDaoImpl;
import com.hope.systemManager.approveManager.model.ApproveContentHeader;
import com.hope.systemManager.frameManager.action.LoginAction;
import com.hope.systemManager.userManager.dao.UserDaoImpl;
import com.hope.systemManager.userManager.model.User;
import com.hope.systemManager.userManager.service.UserService;
import com.hope.systemManager.userManager.service.UserServiceImpl;

public class Test1 {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginAction loginAction = (LoginAction) ctx
				.getBean("loginAction");
		
		loginAction.setUsername("1234");
		loginAction.setPassword("421");
		loginAction.login();
		
//		User user=new User();
//		user.setUsercode("21");
//		user.setPassword("123");
//		loginAction.getUserService().add(user);
		
	}
	@Test
	public void testAdd(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ApproveContentHeaderDaoImpl approveContentHeaderDaoImpl = (ApproveContentHeaderDaoImpl) ctx
//				.getBean("approveContentHeaderDaoImpl");
//		
//		
//		approveContentHeaderDaoImpl.submitterQuery("bibo");
//		User user=new User();
//		user.setUsercode("21");
//		user.setPassword("123");
//		
//		userDaoImpl.add(user);
		
		query();
	}
	
	public void query(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) ctx
				.getBean("sessionFactory");
		
		Session session=sessionFactory.getCurrentSession();
		//Transaction tx =(Transaction) ctx.getBean("txManager");
		Transaction tx =session.beginTransaction();
		
		Query query=null;
		System.out.println("------------11-------------------");
		query=(Query) session.createQuery("from APPROVE_CONTENT_HEADER a where a.submitter='bibo' order by a.contentHeaderId desc");
		query.setCacheable(true);
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		List<ApproveContentHeader> list=new ArrayList<ApproveContentHeader>();
		list=query.list();
		session.close();
		
		SessionFactory sessionFactory1 = (SessionFactory) ctx
				.getBean("sessionFactory");
		
		Session session1=sessionFactory.getCurrentSession();
		//Transaction tx =(Transaction) ctx.getBean("txManager");
		Transaction tx1 =session1.beginTransaction();
		System.out.println("------------2-------------------");
		Query query2=null;
		query2=(Query) session1.createQuery("from APPROVE_CONTENT_HEADER a where a.submitter='bibo' order by a.contentHeaderId desc");
		query2.setCacheable(true);
		query2.setFirstResult(0);
		query2.setMaxResults(1);
		
		List<ApproveContentHeader> list1=new ArrayList<ApproveContentHeader>();
		list1=query2.list();
		session1.close();
	}

}
