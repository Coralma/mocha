package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.basic.dao.LinkedinConnectionNetworkUpdateDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;

public class LinkedinScheduleTimterTask extends TimerTask {
	private LinkedInAccessToken token;
	private LinkedinPersonProfile profile;
	static LinkedinConnectionNetworkUpdateDao updateDao = SpringContextUtils.getBean(LinkedinConnectionNetworkUpdateDao.class);
	LinkedinConnectionDao connDao = SpringContextUtils.getBean(LinkedinConnectionDao.class);

	public LinkedinScheduleTimterTask(LinkedinPersonProfile profile, LinkedInAccessToken token) {
		this.profile = profile;
		this.token = token;
	}

	@Override
	public void run() {
		System.out.println("Start run the backend job");
		LinkedinImpl imple = new LinkedinImpl();
		profile = reloadProfile(profile);
		List<LinkedinConnection> conns = profile.getLinkedinConnections();
		List<LinkedinConnectionNetworkUpdate> updates = imple.getFollowedConNetworkUpdate(token, conns);
		for (LinkedinConnectionNetworkUpdate update : updates) {
			String firstNameUpdate = update.getFirstName().trim();
			String lastNameUpdate = update.getLastName().trim();
			for (LinkedinConnection con : conns) {
				// only focus on the followed connections
				if (con.getNeedFollow() != null && (con.getNeedFollow().equals("00000001") || (con.getNeedFollow().equals(true)))) {
					String firstNameCon = con.getFirstName().trim();
					String lastNameCon = con.getLastName().trim();
					if (firstNameCon.equals(firstNameUpdate) && lastNameCon.equals(lastNameUpdate)) {
						if (!updateDao.findDuplicateUpdate(con, update)) {
							// con.getLinkedinConnectionNetworkUpdate().add(update);
							// connDao.merge(con);
							update.setLinkedinConnection(con);
							updateDao.merge(update);
						}
					}
				}
			}
		}
		System.out.println("");
	}

	private LinkedinPersonProfile reloadProfile(LinkedinPersonProfile profile) {
		List<LinkedinConnection> connections = connDao.findFollowedConnectionByPerson(profile);
		profile.setLinkedinConnections(connections);
		return profile;
	}

	class UpdteNetworkStatus extends Thread {

		public void run() {
			System.out.println("Start to udpate the linkedin network status");
		}
	}

}
