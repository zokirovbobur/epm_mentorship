package com.epam.mvc.dto;

import com.epam.mvc.dao.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "event")
@XmlType(propOrder = {
		"eventName",
		"startDate",
		"endDate",
		"limitOfTicket",
		"ticketPrice"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	private String eventName;

	private String startDate;

	private String endDate;

	private int limitOfTicket;

	private double ticketPrice;

	public EventEntity toEntity(){
		return new EventEntity(eventName, startDate, endDate, limitOfTicket, ticketPrice);
	}
}
