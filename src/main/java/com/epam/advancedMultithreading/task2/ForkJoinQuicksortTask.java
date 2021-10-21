package com.epam.advancedMultithreading.task2;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ForkJoinQuicksortTask extends RecursiveAction {
	int[] a;
	int left;
	int right;

	public ForkJoinQuicksortTask(int[] a) {
		this(a, 0, a.length - 1);
	}

	public ForkJoinQuicksortTask(int[] a, int left, int right) {
		this.a = a;
		this.left = left;
		this.right = right;
	}

	@Override
	protected void compute() {
		if (serialThresholdMet()) {
			Arrays.sort(a, left, right + 1);
		} else {
			int pivotIndex = partition(a, left, right);
			ForkJoinQuicksortTask t1 = new ForkJoinQuicksortTask(a, left,
					pivotIndex - 1);
			ForkJoinQuicksortTask t2 = new ForkJoinQuicksortTask(a, pivotIndex + 1,
					right);
			t1.fork();
			t2.compute();
			t1.join();
		}
	}

	int partition(int[] a, int p, int r) {
		int i = p - 1;
		int x = a[r];
		for (int j = p; j < r; j++) {
			if (a[j] < x) {
				i++;
				swap(a, i, j);
			}
		}
		i++;
		swap(a, i, r);
		return i;
	}

	void swap(int[] a, int p, int r) {
		int t = a[p];
		a[p] = a[r];
		a[r] = t;
	}

	private boolean serialThresholdMet() {
		return right - left < 100000000;
	}
}
