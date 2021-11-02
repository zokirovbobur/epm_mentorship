package com.epam.advancedMultithreading.task4;

import java.util.List;
import java.util.concurrent.*;

public class Task4Main {
	public static void main(String[] args) {
		CompletionStage<List<Employee>> hiredEmployees = EmployeeService.hiredEmployees();
		CompletionStage<List<Employee>> filledEmployees = EmployeeService.fillingSalary();

		CompletableFuture<List<Employee>> c1 = hiredEmployees.toCompletableFuture();
		CompletableFuture<List<Employee>> c2 = filledEmployees.toCompletableFuture();

		c1.join();
		c2.join();

		try {
			if (c1.get().size() > 0)
				EmployeeService.printEmployees();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
