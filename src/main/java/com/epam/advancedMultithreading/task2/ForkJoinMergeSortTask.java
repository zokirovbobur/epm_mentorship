package com.epam.advancedMultithreading.task2;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ForkJoinMergeSortTask extends RecursiveAction {
	int[] a;
	int start;
	int end;

	public ForkJoinMergeSortTask(int[] a, int start, int end) {
		this.a = a;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		final int range = end - start;
		if (serialThresholdMet()){
			Arrays.sort(a, start, end + 1);
		} else {
			final int midPoint = start + (range / 2);
			final ForkJoinMergeSortTask left = new ForkJoinMergeSortTask(a, start, midPoint);
			left.fork();
			final ForkJoinMergeSortTask right = new ForkJoinMergeSortTask(a, midPoint + 1, end);
			right.compute();
			left.join();
			int[] temp = new int[a.length];
			merge(a, temp, start, midPoint, end);
		}
	}

	private static void merge(int[] a, int[] temp, int low, int mid, int high) {
		for (int i = low; i <= high; i++) {
			temp[i] = a[i];
		}
		int i = low, j = mid + 1;
		for (int k = low; k <= high; k++) {
			if (i > mid) {
				a[k] = temp[j++];
			} else if (j > high) {
				a[k] = temp[i++];
			} else if (temp[i] < temp[j]) {
				a[k] = temp[i++];
			} else {
				a[k] = temp[j++];
			}
		}
	}

	private boolean serialThresholdMet() {
		return end - start < 100000000;
	}
}
