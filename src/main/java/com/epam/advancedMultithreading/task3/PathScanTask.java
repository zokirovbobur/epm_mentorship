package com.epam.advancedMultithreading.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PathScanTask extends RecursiveAction {
	private static int fileCount = 0;
	private static int folderCount = 0;
	private static long sumOfFileSize = 0;
	private static boolean endOfScan = false;
	private File file;

	public PathScanTask(File file) {
		this.file = file;
	}

	public static void main (String[] args) {

		new Thread(()->{
			long time = System.currentTimeMillis();
			PathScanTask pathScanTask = new PathScanTask(new File("./"));
			ForkJoinPool.commonPool().invoke(pathScanTask);
			System.out.print("\b\b\b\b\b\b\b\b\b\b");
			System.out.println();
			System.out.println(pathScanTask);
			System.out.println("time taken : "+(System.currentTimeMillis()-time));
			endOfScan = true;
		}).start();
		while (!endOfScan){
			try {
				System.out.print("Loading.");
				Thread.sleep(100);
				System.out.print("\b\b\b\b\b\b\b\b");
				System.out.print("Loading..");
				Thread.sleep(100);
				System.out.print("\b\b\b\b\b\b\b\b\b");
				System.out.print("Loading...");
				Thread.sleep(100);
				System.out.print("\b\b\b\b\b\b\b\b\b\b");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void compute () {
		List<PathScanTask> tasks = new ArrayList<>();
		File[] files = file.listFiles();
		if (files != null)
			for (File f : files) {

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (f.isDirectory()) {
					folderCount++;
					PathScanTask newTask = new PathScanTask(f);
					tasks.add(newTask);
					newTask.fork();
				}
				if (f.isFile()) {
					fileCount++;
					long bytes = 0;
					try {
						bytes = Files.size(f.toPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
					sumOfFileSize += bytes;
				}
			}
		if (tasks.size() > 0) {
			for (PathScanTask task : tasks) {
				task.join();
			}
		}
	}

	@Override
	public String toString() {
		return "ForkJoinPathScanTask{" +
				       "fileCount=" + fileCount +
				       ", folderCount=" + folderCount +
				       ", sumOfFileSize=" + sumOfFileSize +
				       '}';
	}
}