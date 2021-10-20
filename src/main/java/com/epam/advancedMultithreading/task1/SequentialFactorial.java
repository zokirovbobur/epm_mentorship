package com.epam.advancedMultithreading.task1;

import java.math.BigInteger;

public class SequentialFactorial {

	static BigInteger calculateFactorial(BigInteger n) {
		if (n.compareTo(BigInteger.valueOf(2)) <= 0) {
			return n;
		}

		return n.multiply(calculateFactorial(n.subtract(BigInteger.ONE)));
	}
}
