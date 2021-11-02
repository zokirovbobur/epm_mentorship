package com.epam.mvc.repository;

import com.epam.mvc.dao.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
