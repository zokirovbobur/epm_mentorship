package com.epam.task5;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Test {
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	private String name;

}
