package com.epam.task7.service;

import com.epam.task7.exception.BadRequestInputException;
import com.epam.task7.exception.UserCustomeException;
import com.epam.task7.message.ERROR;
import com.epam.task7.model.User;
import com.epam.task7.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User createUser(User user) {
		
		if(!user.getUserName().contains("@")) {
			throw new UserCustomeException(ERROR.USER_NAME_INCORRECT.getCode(), MessageFormat.format(ERROR.USER_NAME_INCORRECT.getErrorMessage(), user.getUserName()));
		}
		
		return userRepo.save(user);
		
	}
	
	public User getUser(long id) {
			Optional<User> user = userRepo.findById(id);
			
			return user.orElseThrow(() ->
			new BadRequestInputException(ERROR.USER_NOT_FOUND.getCode(),MessageFormat.format(ERROR.USER_NOT_FOUND.getErrorMessage(), Long.toString(id)))
					);

	}

}
