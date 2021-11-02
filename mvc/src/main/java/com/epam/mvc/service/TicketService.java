package com.epam.mvc.service;

import com.epam.mvc.dao.TicketEntity;
import com.epam.mvc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements GeneralService<TicketEntity> {
	@Autowired
	private TicketRepository repository;

	@Override
	public Optional<TicketEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public TicketEntity save(TicketEntity object) {
		return repository.save(object);
	}

	@Override
	public List<TicketEntity> getAll() {
		return repository.findAll();
	}
}
