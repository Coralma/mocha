package com.mocha.cooperate.service.pollService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;

public class PollService {

	private BasicUser basicUser;

	private int num = 0;

	private static ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance("threadPoolManager ");
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
	private static HashSet<BasicUser> userHistory = new HashSet<BasicUser>();
	private static SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);
	private static PollService pollService;

	public static synchronized PollService getPollServiceInstance() {
		if (pollService == null) {
			return new PollService();
		}
		return null;
	}

	public PollService() {

	}

	public void startJob(MochaTask mochaTask) {
		// scheduler.scheduleAtFixedRate(new Runnable() {
		// public void run() {
		// System.out.println("Start Job Now");
		// boolean needToStartTask = false;
		// if (hasIncoming()) {
		// System.out.println("New Incoming");
		// MochaTask mochaTask=null;
		// try {
		// mochaTask = (MochaTask) workQueue.take();
		// }
		// catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println("User : " + mochaTask.getBu());
		// System.out.println("Start to poll the services on Facebook");
		// System.out.println("Poll Service Number: " + num++);
		// if (!userHistory.contains(mochaTask.getBu()) || userHistory.size() == 0) {
		// if (mochaTask != null && mochaTask.getBu() != null) {
		// userHistory.add(mochaTask.getBu());
		// SoicalApp soicalApp = saDao.findSoicaAppByName(basicUser, APIKeys.facebookAPIName);
		// if (soicalApp != null && soicalApp.getFacebookFriends().size() > 0) {
		// needToStartTask = true;
		// }
		// }
		// }
		// needToStartTask = true;
		// if (needToStartTask) {
		// threadPoolManager.getMsgQueue().add(mochaTask);
		// }
		// }
		// }
		//
		// }, 0, 1, TimeUnit.SECONDS);

		boolean needToStartTask = false;
		System.out.println("Start to poll the services on Facebook");
		if (!userHistory.contains(mochaTask.getBu()) || userHistory.size() == 0) {
			if (mochaTask != null && mochaTask.getBu() != null) {
				userHistory.add(mochaTask.getBu());
				SoicalApp soicalApp = saDao.findSoicaAppByName(mochaTask.getBu(), APIKeys.facebookAPIName);
				if (soicalApp != null && soicalApp.getFacebookFriends().size() > 0) {
					needToStartTask = true;
				}
			}
		}
		if (needToStartTask) {
			threadPoolManager.getMsgQueue().add(mochaTask);
		}
	}

	public static BlockingQueue<Runnable> getWorkQueue() {
		return workQueue;
	}

	public static void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		PollService.workQueue = workQueue;
	}

	public static boolean hasIncoming() {
		return !getWorkQueue().isEmpty();
	}
}
