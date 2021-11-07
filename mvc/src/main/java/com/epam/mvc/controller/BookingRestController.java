package com.epam.mvc.controller;

import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.dto.EventsInit;
import com.epam.mvc.dto.GeneralAnswer;
import com.epam.mvc.service.EventService;
import com.epam.mvc.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("api")
public class BookingRestController {
	@Autowired
	private EventService eventService;

	@Autowired
	private FilesStorageService storageService;

	@PostMapping("/events/add")
	public ResponseEntity<GeneralAnswer> loadData(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			String uri = storageService.save(file);
			List<EventEntity> eventEntities = EventsInit.convertXMLToEvents(uri);
			eventEntities.forEach(event -> eventService.save(event));

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new GeneralAnswer(message));
		} catch (Exception e) {
			e.printStackTrace();
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GeneralAnswer(message));
		}
	}
}
