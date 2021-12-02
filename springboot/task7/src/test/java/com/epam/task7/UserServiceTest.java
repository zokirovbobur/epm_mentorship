package com.epam.task7;

import com.epam.task7.model.User;
import com.epam.task7.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Test
	public void createUserTest() throws Exception{
		User bbro = new User();
		bbro.setUserName("bbro@user.com");
		bbro.setFirstName("Bobur");
		bbro.setLastName("Zokirov");
		bbro = service.createUser(bbro);
		assert(bbro.getId() != null);
	}

	@Test
	public void fetchUserTest(){
		User user = service.getUser(1L);
		assert(user.getId() != null);
	}
}
