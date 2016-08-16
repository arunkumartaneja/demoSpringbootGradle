package demo.entity;

import java.io.Serializable;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User(String name) {
		this.name = name;
	}
	
}
