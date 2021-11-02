package com.epam.mvc.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class TicketEntity extends BaseEntity{
	@ManyToOne
	private EventEntity event;

	@ManyToOne
	private UserEntity attendee;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		TicketEntity ticketEntity = (TicketEntity) o;
		return getId() != null && Objects.equals(getId(), ticketEntity.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
