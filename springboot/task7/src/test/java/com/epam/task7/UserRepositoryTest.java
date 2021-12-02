package com.epam.task7;

import com.epam.task7.model.User;
import com.epam.task7.respository.UserRepository;
import com.epam.task7.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void createUserTest() throws Exception{
		User bbro = new User();
		bbro.setUserName("bbro@user.com");
		bbro.setFirstName("Bobur");
		bbro.setLastName("Zokirov");
		bbro = repository.save(bbro);
		assert(bbro.getId() != null);
	}

	@Test
	public void fetchUserTest(){
		Optional<User> user = repository.findById(1L);
		assert(user.isPresent());
	}
}
