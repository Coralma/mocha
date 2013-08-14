package com.mocha.soicalAPI;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.ibm.icu.impl.CalendarCache;

public class LinkedinScheduleTimerTask extends MochaTask {
	private LinkedInAccessToken token;
	private LinkedinPersonProfile profile;
	private long runInternval = 1000 * 10;
	private String name = "defaultName";

	// static LinkedinConnectionNetworkUpdateDao updateDao = SpringContextUtils.getBean(LinkedinConnectionNetworkUpdateDao.class);
	// LinkedinConnectionDao connDao = SpringContextUtils.getBean(LinkedinConnectionDao.class);

	public LinkedinScheduleTimerTask(LinkedinPersonProfile profile, LinkedInAccessToken token) {
		this.profile = profile;
		this.token = token;
	}

	LinkedinScheduleTimerTask() {

	}

	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdteNetworkStatus update=new UpdteNetworkStatus();
				update.run();
			}
		}, 1000, 1000 * 10);
	}

	public long getRunInternval() {
		return runInternval;
	}

	public void setRunInternval(long runInternval) {
		this.runInternval = runInternval;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	class UpdteNetworkStatus extends Thread {

		public void run() {
//			System.out.println("Start to udpate the linkedin network status");
		}
	}

}
