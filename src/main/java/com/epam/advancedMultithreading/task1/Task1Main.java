package com.epam.advancedMultithreading.task1;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class Task1Main {
    public static void main(String[] args) {
        System.out.println("Sequental");
        long start = System.nanoTime();
        System.out.println(FactorialTask.calculateFactorial(BigInteger.valueOf(10)));
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
}


