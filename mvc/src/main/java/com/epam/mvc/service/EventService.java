package com.epam.mvc.service;

import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EventService implements GeneralService<EventEntity> {

	@Autowired
	private EventRepository repository;

	@Override
	public Optional<EventEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public EventEntity save(EventEntity object) {
		return repository.save(object);
	}

	@Override
	public List<EventEntity> getAll() {
		return repository.findAll();
	}
}
