package com.epam.mvc.booking;

import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.dao.TicketEntity;
import com.epam.mvc.dao.UserEntity;
import com.epam.mvc.dto.GeneralAnswer;
import com.epam.mvc.dto.UserDto;
import com.epam.mvc.service.EventService;
import com.epam.mvc.service.TicketService;
import com.epam.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingFacadeImpl implements BookingFacade{
	private final UserService userService;
	private final EventService eventService;
	private final TicketService ticketService;

	//TODO write logic for booking
	@Override
	public GeneralAnswer<Ticket> booking(UserEntity attendee, EventEntity event) {
		return null;
	}

	@Override
	public GeneralAnswer<List<EventEntity>> listEvents() {
		return new GeneralAnswer<>(eventService.getAll());
	}

	@Override
	public GeneralAnswer<List<TicketEntity>> listTickets() {
		return new GeneralAnswer<>(ticketService.getAll());
	}

	@Override
	public GeneralAnswer<UserEntity> signIn(String username, String password) {
		Optional<UserEntity> userEntity = userService.findByUserName(username);
		if (userEntity.isPresent()){
			if (userEntity.get().getPassword().equals(password)){
				return new GeneralAnswer<>(userEntity.get());
			}
			return new GeneralAnswer<>(null, "password is incorrect", null);
		}
		return new GeneralAnswer<>(null, "userName is not found", null);
	}

	@Override
	public GeneralAnswer<UserEntity> createUser(UserDto userDto) {
		if (userService.findByUserName(userDto.getUserName()).isPresent()){
			return new GeneralAnswer<>(null, "userName is already taken", userService.save(userDto.toEntity()));
		}
		return new GeneralAnswer<>(userService.save(userDto.toEntity()));
	}
}
