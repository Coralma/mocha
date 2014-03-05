package com.mocha.cooperate.service.pollService;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
	private static Hashtable<String, ThreadPoolManager> tb = new Hashtable<String, ThreadPoolManager>();

	//
	private final static int CORE_POOL_SIZE = 5;

	//
	private final static int MAX_POOL_SIZE = 20;

	//
	private final static int KEEP_ALIVE_TIME = 180;

	//
	private final static int WORK_QUEUE_SIZE = 10000;

	//
	private Queue<Runnable> msgQueue = new LinkedList<Runnable>();

	//
	final Runnable accessBufferThread = new Runnable() {
		public void run() {
			//
			if (hasMoreAcquire()) {

				startJob(msgQueue.poll());
			}
		}

		private void startJob(final Runnable runnable) {
			scheduler.scheduleAtFixedRate(new Runnable() {
				public void run() {
					threadPool.execute(runnable);
				}
			}, 10 * 60, 10 * 60, TimeUnit.SECONDS);
		}
	};

	// handler
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println(r + " request return to the pool " + r);
			getMsgQueue().offer(r);
		}
	};

	//
	final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(
			WORK_QUEUE_SIZE), this.handler);

	//
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	//
	final ScheduledFuture taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, 1, TimeUnit.SECONDS);

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public static synchronized ThreadPoolManager getInstance(String key) {
		ThreadPoolManager obj = tb.get(key);
		if (obj == null) {
			System.out.println("new thread pool :" + key);
			obj = new ThreadPoolManager();
			tb.put(key, obj);
		}

		return obj;
	}

	private ThreadPoolManager() {
	}

	private boolean hasMoreAcquire() {
		return !getMsgQueue().isEmpty();
	}

	public void addTask(Runnable task) {
		threadPool.execute(task);
	}

	public Queue<Runnable> getMsgQueue() {
		return msgQueue;
	}

	public void setMsgQueue(Queue<Runnable> msgQueue) {
		this.msgQueue = msgQueue;
	}
}
