package com.epam.mvc.controller;

import com.epam.mvc.booking.BookingFacade;
import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.dto.GeneralAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("booking")
public class BookingController {
	@Autowired
	private BookingFacade service;

	@GetMapping("events")
	public GeneralAnswer<List<EventEntity>> getEvents(){
		return service.listEvents();
	}
}
