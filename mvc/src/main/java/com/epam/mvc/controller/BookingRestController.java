package com.epam.mvc.controller;

import com.epam.mvc.booking.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/booking")
public class BookingRestController {
	@Autowired
	private BookingFacade service;

}
