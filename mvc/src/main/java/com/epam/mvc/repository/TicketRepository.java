package com.epam.mvc.repository;

import com.epam.mvc.dao.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
