package com.epam.advancedMultithreading.task4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	private int empId;
	private String naming;
	private double salary;

	public Employee(int empId, String naming) {
		this.empId = empId;
		this.naming = naming;
	}
}
