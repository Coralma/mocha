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

// Implement the working queue later 
public class PollService {

	private BasicUser basicUser;
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

	private synchronized void executeMochaTask(MochaTask mochaTask) {
		PollService pollService = PollService.getPollServiceInstance();
		// FBStatusUpdatechedulerTask mochaTask = new FBStatusUpdatechedulerTask(eventBus.getUser());
		boolean needToStartTask = true;

		for (BasicUser bu : userHistory) {
			if (bu.getUserName().equals(mochaTask.getBu().getUserName())) {
				needToStartTask = false;
				break;
			}
		}
		if (needToStartTask) {
			SoicalApp soicalApp = saDao.findSoicaAppByName(mochaTask.getBu(), APIKeys.facebookAPIName);
			if (soicalApp != null && soicalApp.getFacebookFriends().size() > 0) {
				userHistory.add(mochaTask.getBu());
				System.out.println("Start new thread for facebook polling service ");
				threadPoolManager.getMsgQueue().add(mochaTask);
			}
		}
	}

	public void addTask(MochaTask mochaTask) {
		executeMochaTask(mochaTask);
		// boolean needToStartTask = false;
		// if (!userHistory.contains(mochaTask.getBu()) || userHistory.size() == 0) {
		// if (mochaTask != null && mochaTask.getBu() != null) {
		// userHistory.add(mochaTask.getBu());
		// SoicalApp soicalApp = saDao.findSoicaAppByName(mochaTask.getBu(), APIKeys.facebookAPIName);
		// if (soicalApp != null && soicalApp.getFacebookFriends().size() > 0) {
		// needToStartTask = true;
		// }
		// }
		// }
		// if (needToStartTask) {
		// System.out.println("Start new thread for facebook polling service ");
		// threadPoolManager.getMsgQueue().add(mochaTask);
		// }
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
