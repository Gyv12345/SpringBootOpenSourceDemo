package cn.yt4j.design;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * @author shichenyang
 */
public class SemaphoreExample {

	/**
	 * 允许最多 3 个线程同时访问
	 */
	private static final Semaphore SEMAPHORE = new Semaphore(3);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Task(i)).start();
		}
	}

	static class Task implements Runnable {

		private int threadId;

		public Task(int threadId) {
			this.threadId = threadId;
		}

		@Override
		public void run() {
			try {
				// 请求获取信号量
				SEMAPHORE.acquire();
				System.out.println("Thread " + threadId + " is working.");
				// 模拟临界区的工作，线程持有信号量的期间不能有超过 3 个线程访问
				Thread.sleep(2000);
				System.out.println("Thread " + threadId + " finished working.");
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			finally {
				// 释放信号量
				SEMAPHORE.release();
			}
		}

	}

}
