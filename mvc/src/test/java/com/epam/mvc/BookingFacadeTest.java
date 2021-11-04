package com.epam.mvc;

import com.epam.mvc.booking.BookingFacade;
import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.dao.TicketEntity;
import com.epam.mvc.dao.UserEntity;
import com.epam.mvc.dto.GeneralAnswer;
import com.epam.mvc.dto.UserDto;
import com.epam.mvc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookingFacadeTest {
	@Autowired
	private BookingFacade service;

	@Autowired
	private UserService userService;

	@Test
	public void listTicketsTest(){
		GeneralAnswer<List<TicketEntity>> listTickets = service.listTickets();
		assert(listTickets.getObject().size() == 0 && listTickets.getResult().equals("success"));
	}

	@Test
	public void bookingTest(){
		UserEntity user = signInTest();
		List<EventEntity> listEvents = listEventsTest();

		GeneralAnswer<TicketEntity> ticket1 = service.booking(user.getId(), listEvents.get(0).getId());
		assert (ticket1.getResult().equals("success") && ticket1.getObject() != null);
		GeneralAnswer<TicketEntity> ticket2 = service.booking(user.getId(), listEvents.get(0).getId());
		assert (ticket2.getResult().equals("success") && ticket2.getObject() != null);

		GeneralAnswer<TicketEntity> ticket3 = service.booking(user.getId(), listEvents.get(0).getId());
		assert (ticket3.getError().equals("tickets are over") && ticket3.getObject() == null);

		GeneralAnswer<TicketEntity> ticket4 = service.booking(user.getId(), -1L);
		assert (ticket4.getError().equals("event not found with given event id")
				        && ticket4.getObject() == null);

		GeneralAnswer<TicketEntity> ticket5 = service.booking(-1L,  listEvents.get(0).getId());
		assert (ticket5.getError().equals("user not found with given user id")
				        && ticket5.getObject() == null);

		GeneralAnswer<TicketEntity> ticket6 = service.booking(-1L,  -1L);
		assert (ticket6.getError().equals("user not found with given user id")
				        && ticket6.getObject() == null);
	}

	@Test
	public UserEntity signInTest(){
		UserDto userDto = new UserDto();
		userDto.setUserName("david");
		userDto.setPassword("smith");
		userDto.setFullName("David Smith");
		GeneralAnswer<UserEntity> createdUser = service.createUser(userDto);

		GeneralAnswer<UserEntity> user = service.signIn("david", "smith");
		assert(user.getObject() != null && user.getResult().equals("success"));

		GeneralAnswer<UserEntity> userIncorrectPassword
				= service.signIn("david", "doa");
		assert(userIncorrectPassword.getObject() == null
				       && userIncorrectPassword.getError().equals("password is incorrect"));

		GeneralAnswer<UserEntity> userIncorrectUserName
				= service.signIn("smith", "doe");
		assert(userIncorrectUserName.getObject() == null
				       && userIncorrectUserName.getError().equals("username is not found"));

		GeneralAnswer<UserEntity> userIncorrectUserNameAndPassword
				= service.signIn("smith", "doa");
		assert(userIncorrectUserNameAndPassword.getObject() == null
				       && userIncorrectUserNameAndPassword.getError().equals("username is not found"));
		return user.getObject();
	}

	@Test
	public void createUserTest(){
		UserDto userDto = new UserDto();
		userDto.setUserName("john");
		userDto.setPassword("doe");
		userDto.setFullName("John Doe");
		GeneralAnswer<UserEntity> createdUser = service.createUser(userDto);
		assert(createdUser.getObject() != null && createdUser.getResult().equals("success"));
	}

	@Test
	public List<EventEntity> listEventsTest(){
		GeneralAnswer<List<EventEntity>> listEvents = service.listEvents();
		assert(listEvents.getObject().size() > 0 && listEvents.getResult().equals("success")) ;
		return listEvents.getObject();
	}
}
