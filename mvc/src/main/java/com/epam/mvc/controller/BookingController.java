package com.epam.mvc.controller;

import com.epam.mvc.booking.BookingFacade;
import com.epam.mvc.dao.TicketEntity;
import com.epam.mvc.exception.BadRequestException;
import com.epam.mvc.exception.ErrorView;
import com.epam.mvc.util.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.OperationNotSupportedException;
import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping
public class BookingController {
	@Autowired
	private BookingFacade service;

	@ErrorView(value = "test-error-view", status = HttpStatus.GONE)
	@GetMapping
	public String eventList(Model model){
		model.addAttribute("events", service.listEvents().getObject());
		return "events";
	}

	@ErrorView(value = "test-error-view", status = HttpStatus.GONE)
	@GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> ticketsReport() {

		List<TicketEntity> eventEntities = service.listTickets().getObject();

		ByteArrayInputStream bis = GeneratePdfReport.citiesReport(eventEntities);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=ticketsReport.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException (OperationNotSupportedException e) {
		return "exception :" + e.toString();
	}
}
