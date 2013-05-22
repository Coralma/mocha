package com.mocha.cooperate.service.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.service.ForumService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ForumServiceTest {

	@Test
	public void testQueryLastThreeNotification() {
		ForumService forumService = new ForumService();
		List<Discuss> notifyResults = forumService.queryLastThreeNotification();
		for(Discuss notify : notifyResults) {
			Assert.assertEquals(notify.getStatus(), "2");
		}
		System.out.println(notifyResults);

		List<Discuss> questResults = forumService.queryLastThreeQuestion();
		for(Discuss quest : questResults) {
			Assert.assertEquals(quest.getStatus(), "1");
		}
		System.out.println(questResults);

		List<Discuss> discussResults = forumService.queryLastThreeDiscuss();
		for(Discuss discuss : discussResults) {
			Assert.assertEquals(discuss.getStatus(), "0");
		}
		System.out.println(discussResults);
	}
}
