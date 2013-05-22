package com.mocha.cooperate.service.test;

import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.service.UserService;

/**
 * 
 * @author Coral.Ma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TimeLineServiceTest {

//	@Before
//    public void setUp() throws Exception {
////		ApplicationContext context = new FileSystemXmlApplicationContext("D:/coral-software/mocha/projects/cooperate/src/main/webapp/WEB-INF/applicationContext.xml");
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"/src/main/webapp/WEB-INF/applicationContext.xml"});
//    }
	
	List<Attachment> attachments = Lists.newArrayList();

//	@Test
	public void testSaveStatus() {
		TimeLineService timeLineService = new TimeLineService();
		UserService userService = new UserService();
				
		List<BasicUser> basicUsers = userService.loadAllBasicUser();
		Status status = new Status();
		Set<BasicUser> notifiedUsers = Sets.newHashSet(basicUsers);
		timeLineService.saveStatus(status, basicUsers.get(0), notifiedUsers, attachments);
	}
	
//	@Test
	public void testUpdateStatus() {
		TimeLineService timeLineService = new TimeLineService();
		UserService userService = new UserService();
		
		List<BasicUser> basicUsers = userService.loadAllBasicUser();
		List<TimeLine> timelines = timeLineService.loadTimeLine(basicUsers.get(0));
		for(int i=0; i<timelines.size();i++) {
			TimeLine timeLine = timelines.get(i);
			Status status = timeLine.getStatus();
			Comment comment = new Comment();
			comment.setContent("Testing reply content.");
			comment.setCreator(basicUsers.get(0));
			status.getComments().add(comment);
			comment.setStatus(status);
			timeLineService.updateStatus(status);
		}
	}
	
//	@Test
	public void testRemoveTimeLine() {
		TimeLineService timeLineService = new TimeLineService();
		UserService userService = new UserService();
		
		List<BasicUser> basicUsers = userService.loadAllBasicUser();
		List<TimeLine> timelines = timeLineService.loadTimeLine(basicUsers.get(0));
		for(int i=0; i<timelines.size();i++) {
			timeLineService.removeTimeLine(timelines.get(i));
		}
	}
	
//	@Test
	public void testLoadTimeLine() {
		TimeLineService timeLineService = new TimeLineService();
		UserService userService = new UserService();
		
		List<BasicUser> basicUsers = userService.loadAllBasicUser();
		List<TimeLine> timelines = timeLineService.loadTimeLine(basicUsers.get(0), 0);
		System.out.println(timelines);
	}
	
	@Test
	public void testLoadStatus() {
		TimeLineService timeLineService = new TimeLineService();
		UserService userService = new UserService();
		
		List<BasicUser> basicUsers = userService.loadAllBasicUser();
		List<TimeLine> timelines = timeLineService.loadStatus(basicUsers.get(0), 0);
		System.out.println(timelines);
	}
}
