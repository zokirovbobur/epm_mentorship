package com.epam.mvc.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;
@Getter
@ToString
@RequiredArgsConstructor
@Entity
public class EventEntity extends BaseEntity{

	private String eventName;

	private String startDate;

	private String endDate;

	private int limitOfTicket;

	private double ticketPrice;

	public EventEntity(String eventName, String startDate, String endDate, int limitOfTicket, double ticketPrice) {
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.limitOfTicket = limitOfTicket;
		this.ticketPrice = ticketPrice;
	}

	public void decrementTicket(){
		--this.limitOfTicket;
	}

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
