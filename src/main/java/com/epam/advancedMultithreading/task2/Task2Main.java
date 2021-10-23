package com.epam.advancedMultithreading.task2;

import java.util.concurrent.ForkJoinPool;

public class Task2Main {
	public static void main(String[] args) {
		System.out.println("quicksort starts");
		quickSortTest();
		System.out.println("mergesort starts");
		mergeSortTest();
	}

	private static void quickSortTest(){
		ForkJoinPool fjPool = new ForkJoinPool();
		int[] a = new int[100000001];
		for (int i = 0; i < a.length; i++) {
			int k = (int) (Math.random() * 22222);
			a[i] = k;
		}
		ForkJoinQuicksortTask forkJoinQuicksortTask = new ForkJoinQuicksortTask(a,
				0, a.length - 1);
		long start = System.nanoTime();
		fjPool.invoke(forkJoinQuicksortTask);
		System.out.println("Time: " + (System.nanoTime() - start) + " nanoseconds");
	}

	private static void mergeSortTest(){
		ForkJoinPool fjPool = new ForkJoinPool();
		int[] a = new int[100000001];
		for (int i = 0; i < a.length; i++) {
			int k = (int) (Math.random() * 22222);
			a[i] = k;
		}
		ForkJoinMergeSortTask forkJoinQuicksortTask = new ForkJoinMergeSortTask(a,
				0, a.length - 1);
		long start = System.nanoTime();
		fjPool.invoke(forkJoinQuicksortTask);
		System.out.println("Time: " + (System.nanoTime() - start) + " nanoseconds");
	}
}
