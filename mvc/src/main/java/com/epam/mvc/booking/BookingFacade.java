package com.epam.mvc.booking;

import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.dao.TicketEntity;
import com.epam.mvc.dao.UserEntity;
import com.epam.mvc.dto.GeneralAnswer;
import com.epam.mvc.dto.UserDto;
import sun.security.krb5.internal.Ticket;

import java.util.List;

public interface BookingFacade {
	GeneralAnswer<Ticket> booking(UserEntity attendee, EventEntity event);
	GeneralAnswer<List<EventEntity>> listEvents();
	GeneralAnswer<List<TicketEntity>> listTickets();
	GeneralAnswer<UserEntity> signIn(String username, String password);
	GeneralAnswer<UserEntity> createUser(UserDto userDto);
}
