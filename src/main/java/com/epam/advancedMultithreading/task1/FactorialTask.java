package com.epam.advancedMultithreading.task1;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<BigInteger> {

	public static void main(String[] args) {
		System.out.println("Sequental");
		long start = System.nanoTime();
		System.out.println(calculateFactorial(BigInteger.valueOf(10)));
		long end = System.nanoTime();
		long time_sequential = end - start;
		System.out.println(time_sequential + " nanoseconds for sequential");

		System.out.println("FJP");
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		start = System.nanoTime();
		System.out.println(forkJoinPool.invoke(new FactorialTask(BigInteger.valueOf(10))));
		end = System.nanoTime();
		long time_fjp = end - start;
		System.out.println(time_fjp + " nanoseconds for FJP");
		System.out.println("difference of execution times: " + (time_sequential - time_fjp) + " nanoseconds");
	}

	private BigInteger number;

	public FactorialTask(BigInteger number) {
		this.number = number;
	}

	@Override
	protected BigInteger compute() {
		if (number.compareTo(BigInteger.valueOf(1)) <= 0) {
			return calculateFactorial(number);
		}
		FactorialTask subtask = new FactorialTask(number.subtract(BigInteger.ONE));
		subtask.fork();
		return number.multiply(subtask.join());
	}

	public static BigInteger calculateFactorial(BigInteger n) {
		if (n.compareTo(BigInteger.valueOf(2)) <= 0) {
			return n;
		}

		return n.multiply(calculateFactorial(n.subtract(BigInteger.ONE)));
	}
}
