package demo.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import demo.entity.User;


@Repository
public class UserDaoImpl implements UserDao {

	@Override
	@Cacheable(value="userDataCache", key="#name")
	public User getUserData(String name) {
		slowQuery(2000L);
		System.out.println("getUserData is running...");
		return new User("Arun");
	}

	private void slowQuery(long seconds) {
		 try {
             Thread.sleep(seconds);
         } catch (InterruptedException e) {
             throw new IllegalStateException(e);
         }
	}

}
