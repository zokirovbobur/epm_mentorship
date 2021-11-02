package com.epam.mvc.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class EventEntity extends BaseEntity{

	private String eventName;

	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime startDate;

	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime endDate;

	private int limitOfTicket;

	private double ticketPrice;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		EventEntity that = (EventEntity) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
