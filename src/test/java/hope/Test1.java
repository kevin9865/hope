package hope;

import static org.junit.Assert.*;

import org.hibernate.cache.redis.RedisRegionFactory;
import org.hibernate.cache.redis.SingletonRedisRegionFactory;
import org.hibernate.engine.transaction.internal.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
//	@Test
//	public void testAdd(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserDaoImpl userDaoImpl = (UserDaoImpl) ctx
//				.getBean("userDaoImpl");
//		
//		User user=new User();
//		user.setUsercode("21");
//		user.setPassword("123");
//		
//		userDaoImpl.add(user);
//	}

}
