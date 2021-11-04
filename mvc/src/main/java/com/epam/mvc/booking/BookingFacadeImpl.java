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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingFacadeImpl implements BookingFacade{
	private final UserService userService;
	private final EventService eventService;
	private final TicketService ticketService;
	private final Logger log = LoggerFactory.getLogger(BookingFacadeImpl.class);

	@Override
	public GeneralAnswer<TicketEntity> booking(Long attendeeId, Long eventId) {
		log.info("booking: attendeeId: " + attendeeId + " eventId: " + eventId);
		Optional<UserEntity> user = userService.findById(attendeeId);

		if (user.isPresent()){
			log.info("booking: user is found: " + user.get());
			Optional<EventEntity> event = eventService.findById(eventId);

			if (event.isPresent()) {
				log.info("booking: event is found: " + event.get());

				if (event.get().getLimitOfTicket() > 0) {
					log.info("booking: ticket is available");
					EventEntity eventEntity = event.get();
					eventEntity.decrementTicket();
					eventEntity = eventService.save(eventEntity);

					TicketEntity ticketEntity = new TicketEntity(eventEntity, user.get());
					ticketEntity = ticketService.save(ticketEntity);
					log.info("booking: completed");
					return new GeneralAnswer<>(ticketEntity);

				} else {
					log.warn("booking: tickets are over");
					return new GeneralAnswer<>("", "tickets are over", null);
				}

			} else {
				log.warn("booking: event not found with given event id");
				return new GeneralAnswer<>("", "event not found with given event id", null);
			}

		} else {
			log.warn("booking: user not found with given user id");
			return new GeneralAnswer<>("", "user not found with given user id", null);
		}
	}

	@Override
	public GeneralAnswer<List<EventEntity>> listEvents() {
		log.info("list all events");
		return new GeneralAnswer<>(eventService.getAll());
	}

	@Override
	public GeneralAnswer<List<TicketEntity>> listTickets() {
		log.info("list all tickets");
		return new GeneralAnswer<>(ticketService.getAll());
	}

	@Override
	public GeneralAnswer<UserEntity> signIn(String username, String password) {
		log.info("sign in: "+username+" trying to sign in ");
		Optional<UserEntity> userEntity = userService.findByUserName(username);
		if (userEntity.isPresent()){
			log.info("sign in: "+username+" user is found ");
			if (userEntity.get().getPassword().equals(password)){
				log.info("sign in: "+username+" password is correct ");
				return new GeneralAnswer<>(userEntity.get());
			}
			log.warn("sign in: "+username+" password is incorrect ");
			return new GeneralAnswer<>(null, "password is incorrect", null);
		}
		log.warn("sign in: "+username+" userName is not found");
		return new GeneralAnswer<>(null, "username is not found", null);
	}

	@Override
	public GeneralAnswer<UserEntity> createUser(UserDto userDto) {
		if (userService.findByUserName(userDto.getUserName()).isPresent()){
			return new GeneralAnswer<>(null, "userName is already taken", userService.save(userDto.toEntity()));
		}
		return new GeneralAnswer<>(userService.save(userDto.toEntity()));
	}

	@PostConstruct
	public void createInitData(){
		log.info("creating initial events data");

		EventEntity eventForPair = new EventEntity("Marry me celebration",
				LocalDateTime.of(2021,11, 12, 18, 0, 0),
				LocalDateTime.of(2021,11, 12, 21, 59, 59),
				2,
				200.00
		);

		EventEntity eventHalloween = new EventEntity("Halloween",
				LocalDateTime.of(2021,10, 31, 20, 0, 0),
				LocalDateTime.of(2021,10, 31, 23, 59, 59),
				1000,
				200.00
		);

		EventEntity event11 = new EventEntity("Singles' day",
				LocalDateTime.of(2021,11, 11, 20, 0, 0),
				LocalDateTime.of(2021,11, 11, 23, 59, 59),
				100,
				500.00
		);

		EventEntity eventNewYear = new EventEntity("New Year",
				LocalDateTime.of(2021,12, 31, 20, 0, 0),
				LocalDateTime.of(2022,1, 1, 4, 0, 0),
				1000,
				1000.00
		);

		eventService.save(eventForPair);
		eventService.save(eventHalloween);
		eventService.save(event11);
		eventService.save(eventNewYear);
		log.info("created initial events data");
	}
}
