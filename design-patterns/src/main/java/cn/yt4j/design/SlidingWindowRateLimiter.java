package cn.yt4j.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 滑动窗口示例
 *
 * @author shichenyang
 */
public class SlidingWindowRateLimiter {

	// 设置窗口大小为 1 秒
	private final long windowSizeInMillis = 1000;

	// 最大请求数，比如1秒钟内最多允许10个请求
	private final int maxRequests;

	// 队列用于存储每个请求的时间戳
	private final Queue<Long> requestTimestamps;

	public SlidingWindowRateLimiter(int maxRequestsPerWindow) {
		this.maxRequests = maxRequestsPerWindow;
		this.requestTimestamps = new LinkedList<>();

	}

	// 检查是否允许请求
	public synchronized boolean allowRequest() {
		long currentTimeMillis = System.currentTimeMillis();

		// 移除过期的请求（不再在窗口内的请求）
		while (!requestTimestamps.isEmpty() && currentTimeMillis - requestTimestamps.peek() > windowSizeInMillis) {
			requestTimestamps.poll();
		}

		// 判断当前窗口内的请求数是否超过限制
		if (requestTimestamps.size() < maxRequests) {
			// 记录当前请求
			requestTimestamps.offer(currentTimeMillis);
			// 允许请求
			return true;
		}
		else {
			// 拒绝请求
			return false;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 限制每秒最多 5 个请求
		SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(5);

		// 模拟请求
		for (int i = 0; i < 10; i++) {
			if (rateLimiter.allowRequest()) {
				System.out.println("Request " + i + " allowed at " + System.currentTimeMillis());
			}
			else {
				System.out.println("Request " + i + " denied at " + System.currentTimeMillis());
			}
			// 每 200 毫秒发起一个请求
			Thread.sleep(180);
		}
	}

}
