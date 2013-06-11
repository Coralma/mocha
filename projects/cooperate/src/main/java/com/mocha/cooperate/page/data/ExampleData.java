/**
 * 
 */
package com.mocha.cooperate.page.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Lists;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Shotcut;
import com.mocha.cooperate.model.ShotcutItem;
import com.mocha.cooperate.model.Status;

/**
 * @author Administrator
 *
 */
public class ExampleData {

	public static List<Shotcut> getShotcut() {
		List<Shotcut> shotcutList = new ArrayList<Shotcut>();

		// general
		List<ShotcutItem> shotcutItemList = new ArrayList<ShotcutItem>();
		Shotcut shotcut= new Shotcut();
		shotcut.setTitle("cooperate.shotcut.GeneralOperation");
		ShotcutItem shotcutItem = new ShotcutItem(); 
		shotcutItem.setLabel("cooperate.shotcut.MyFeed");
		shotcutItem.setAction(PresenterProperty.HOME);
		shotcutItem.setIcon("icons/home_icon.png");
		shotcutItemList.add(shotcutItem);
		
		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.Notification");
		shotcutItem.setAction(PresenterProperty.NOTIFICATION);
		shotcutItem.setIcon("icons/notify_icon.png");
		shotcutItemList.add(shotcutItem);

		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.Colleague");
		shotcutItem.setAction(PresenterProperty.COLLEAGUE);
		shotcutItem.setIcon("icons/userOper_icon.png");
		shotcutItemList.add(shotcutItem);

		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.Forum");
		shotcutItem.setAction(PresenterProperty.FORUM);
		shotcutItem.setIcon("icons/discuss_icon.png");
		shotcutItemList.add(shotcutItem);
		
		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.Todo");
		shotcutItem.setAction(PresenterProperty.TODO);
		shotcutItem.setIcon("icons/todo-icon.png");
		shotcutItemList.add(shotcutItem);

		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.File");
		shotcutItem.setAction(PresenterProperty.FILE);
		shotcutItem.setIcon("icons/file_icon.png");
		shotcutItemList.add(shotcutItem);
		
		shotcut.setShotcutItems(shotcutItemList);
		shotcutList.add(shotcut);
		
		// application
		shotcutItemList = new ArrayList<ShotcutItem>();
		shotcut= new Shotcut();
		shotcut.setTitle("cooperate.shotcut.Application");
		
		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("Insurance Broker");
		shotcutItem.setAction("ib");
		shotcutItem.setIcon("icons/ib_icon.png");
		shotcutItem.setType(SystemProperty.PAGE_TYPE);
		shotcutItemList.add(shotcutItem);
		
		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.CustomerManage");
		shotcutItem.setAction("crm");
		shotcutItem.setIcon("icons/crm_icon.png");
		shotcutItem.setType(SystemProperty.PAGE_TYPE);
		shotcutItemList.add(shotcutItem);
		
		shotcut.setShotcutItems(shotcutItemList);
		shotcutList.add(shotcut);
		
		// admin
		shotcutItemList = new ArrayList<ShotcutItem>();
		shotcut= new Shotcut();
		shotcut.setTitle("cooperate.shotcut.Administration");
		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.UserOperation");
		shotcutItem.setIcon("icons/userOper_icon.png");
		shotcutItem.setAction(PresenterProperty.USER_PERMISSION);
		shotcutItemList.add(shotcutItem);
		
		shotcutItem = new ShotcutItem();
		shotcutItem.setLabel("cooperate.shotcut.CompanyFile");
		shotcutItem.setIcon("icons/file_icon.png");
		shotcutItem.setAction(PresenterProperty.COMPANY_FILE);
		shotcutItemList.add(shotcutItem);
		
		shotcut.setShotcutItems(shotcutItemList);
		shotcutList.add(shotcut);
		
		
		return shotcutList;
	}
	
	
	public static List<Discuss> getDiscusses() {
		List<Discuss> discusses = Lists.newArrayList();
		Discuss d = new Discuss();
		d.setTitle("How do I forward to another URL from a Jetty handler?");
		d.setContent("Somewhere in our chain of servlet filters there is a filter which forwards the request to the sign-in page when a 401 error is sent, as a usability tweak. I'm trying to convert this to a Jetty handler because someone wants all web applications to be authenticated by the same logic instead of every webapp having to implement their own authentication. (The main reason we're using a filter approach in the first place is that nobody was able to get Jetty's container-level authentication to work at all - we have the ability to choose Windows auth or built-in auth and want to be able to switch between these at runtime and were never able to figure out how to make that work with Jetty.)");
		d.setCreator(getBasicUser());
		d.setCreateTime(new Date());
		discusses.add(d);
		
		d = new Discuss();
		d.setTitle("How do I forward to another URL from a Jetty handler?");
		d.setContent("Somewhere in our chain of servlet filters there is a filter which forwards the request to the sign-in page when a 401 error is sent, as a usability tweak. I'm trying to convert this to a Jetty handler because someone wants all web applications to be authenticated by the same logic instead of every webapp having to implement their own authentication. (The main reason we're using a filter approach in the first place is that nobody was able to get Jetty's container-level authentication to work at all - we have the ability to choose Windows auth or built-in auth and want to be able to switch between these at runtime and were never able to figure out how to make that work with Jetty.)");
		d.setCreator(getBasicUser());
		d.setCreateTime(new Date());
		d.setStatus("Q");
		discusses.add(d);

		d = new Discuss();
		d.setTitle("How do I forward to another URL from a Jetty handler?");
		d.setContent("Somewhere in our chain of servlet filters there is a filter which forwards the request to the sign-in page when a 401 error is sent, as a usability tweak. I'm trying to convert this to a Jetty handler because someone wants all web applications to be authenticated by the same logic instead of every webapp having to implement their own authentication. (The main reason we're using a filter approach in the first place is that nobody was able to get Jetty's container-level authentication to work at all - we have the ability to choose Windows auth or built-in auth and want to be able to switch between these at runtime and were never able to figure out how to make that work with Jetty.)");
		d.setCreator(getBasicUser());
		d.setCreateTime(new Date());
		d.setStatus("I");
		discusses.add(d);
		return discusses;
	}
	
	public static List<Status> getStatus() {
		List<Status> statuses = Lists.newArrayList();
		BasicUser user = new BasicUser();
		user.setRealName("Coral Ma");
		user.setUserPhoto("D:\\cooperateCache\\users\\7d7a6afcgw1du2m43uzzag.gif");
		Status status = new Status();
		status.setCreateTime(new Date());
		status.setContent("Somewhere in our chain of servlet filters there is a filter which forwards the request to the sign-in page when a 401 error is sent, as a usability tweak. I'm trying to convert this to a Jetty handler because someone wants all web applications to be authenticated by the same logic instead of every webapp having to implement their own authentication. (The main reason we're using a filter approach in the first place is that nobody was able to get Jetty's container-level authentication to work at all - we have the ability to choose Windows auth or built-in auth and want to be able to switch between these at runtime and were never able to figure out how to make that work with Jetty.)");
		status.setCreator(user);
		statuses.add(status);
		
		status = new Status();
		status.setCreateTime(new Date());
		status.setContent("Just got done with three laps around Fiesta Island with Rouse.");
		status.setCreator(user);
		statuses.add(status);
		
		user = new BasicUser();
		user.setRealName("Vance Zhao");
		user.setUserPhoto("D:\\cooperateCache\\users\\7b21c683jw1drmj8354aeg.gif");
		status = new Status();
		status.setCreateTime(new Date());
		status.setContent("LeagueOne was migrated out of Amazon's EC2 and into our Vegas Datacenter this week. Major improvement in performance (see graph) and 5x improvement in concurrent user capacity. Kudos to the IT and Dev/QA folks who made it all happen!");
		status.setCreator(user);
		statuses.add(status);
		
		status = new Status();
		status.setCreateTime(new Date());
		status.setContent("Two heads are better than one: The Thriva Dev team and DBA team found some new opportunities for potential speed enhancements. We're looking to roll these into our upcoming release schedule as enhancements. Thanks especially to Simon Wang, Jason Qu, and Robert Luo for your research here!");
		status.setCreator(user);
		statuses.add(status);
		return statuses;
	}
	
	public static BasicUser getBasicUser() {
		BasicUser user = new BasicUser();
		user.setRealName("Coral Ma");
		user.setUserPhoto("D:\\cooperateCache\\users\\7d7a6afcgw1du2m43uzzag.gif");
		return user;
	}
}
