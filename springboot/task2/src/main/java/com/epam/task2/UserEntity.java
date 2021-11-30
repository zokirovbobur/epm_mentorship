package com.epam.task2;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {
	@Id
	@Column(name = "user_id", nullable = false)
	private Long userId;

	private String userName;
	private String userType;
}
