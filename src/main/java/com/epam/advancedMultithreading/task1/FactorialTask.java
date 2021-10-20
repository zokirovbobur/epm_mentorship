package com.epam.advancedMultithreading.task1;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<BigInteger> {

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

	public BigInteger factorialHavingLargeResult(int n) {
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= n; i++)
			result = result.multiply(BigInteger.valueOf(i));
		return result;
	}

	private static BigInteger calculateFactorial(BigInteger n) {
		if (n.compareTo(BigInteger.valueOf(2)) <= 0) {
			return n;
		}

		return n.multiply(calculateFactorial(n.subtract(BigInteger.ONE)));
	}
}
