package com.epam.mvc.dto;

import com.epam.mvc.dao.EventEntity;
import com.epam.mvc.dao.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
	private EventEntity event;
	private UserEntity attendee;
}
