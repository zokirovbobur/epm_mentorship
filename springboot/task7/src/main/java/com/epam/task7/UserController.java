package com.epam.task7;

import com.epam.task7.exception.BadRequestInputException;
import com.epam.task7.exception.UserCustomeException;
import com.epam.task7.model.User;
import com.epam.task7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User userObj = null;
		try {
			userObj = userService.createUser(user);
		} catch (UserCustomeException e) {
			throw new UserCustomeException(e.getErrorCode(), e.getMessage());
		}
		return new ResponseEntity<User>(userObj, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getuser(@PathVariable Long id) {
		User user = null;
		try {
			user = userService.getUser(id);
		} catch (BadRequestInputException e) {
			throw new BadRequestInputException(e.getErrorCode(), e.getMessage());
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
