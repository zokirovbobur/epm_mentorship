package com.epam.advancedMultithreading.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;

public final class EmployeeService {
	private static List<Employee> employees = new ArrayList<>();

	private static void generateEmployees(){
		Employee e1 = new Employee(1,"John Smith  ");
		Employee e2 = new Employee(2,"Brad Pitt   ");
		Employee e3 = new Employee(3,"John Wick   ");
		Employee e4 = new Employee(4,"Harry Potter");
		Employee e5 = new Employee(5,"Peter Parker");
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		employees.add(e5);
	}

	private EmployeeService() {
	}

//	public static CompletableFuture<List<Employee>> hiredEmployees(){
//		CompletableFuture<List<Employee>> completableFuture = new CompletableFuture<>();
//		generateEmployees();
//
//		Executors.newCachedThreadPool().submit(() -> {
//			Thread.sleep(500);
//
//			completableFuture.complete(employees);
//			return null;
//		});
//		return completableFuture;
//	}

	public static CompletionStage<List<Employee>> hiredEmployees(){
		CompletableFuture<List<Employee>> completableFuture = new CompletableFuture<>();
		generateEmployees();

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);

			completableFuture.complete(employees);
			return null;
		});
		return completableFuture;
	}

	public static CompletionStage<List<Employee>> fillingSalary(){
		CompletableFuture<List<Employee>> completableFuture = new CompletableFuture<>();
		for (int i = 0; i < employees.size(); i++) {
			employees.set(i, getSalary(employees.get(i).getEmpId())) ;
		}

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);

			completableFuture.complete(employees);
			return null;
		});
		return completableFuture;
	}

	public static Employee getSalary(int hiredEmployeeId){
		Employee result = null;
		for (Employee employee : employees) {
			if (employee.getEmpId() == hiredEmployeeId) {
				employee.setSalary(10000);
				result = employee;
				break;
			}
		}
		return result;
	}

	public static void printEmployees(){
		System.out.println("Id - employee naming - salary");
		System.out.println("______________________________");
		employees.forEach(e -> System.out.println(e.getEmpId() + "    " + e.getNaming() + "      " + e.getSalary()));
	}
}
