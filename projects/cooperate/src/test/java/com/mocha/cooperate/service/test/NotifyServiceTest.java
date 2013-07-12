/**
 * 
 */
package com.mocha.cooperate.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.service.BasicUserService;
import com.mocha.cooperate.basic.dao.NotifyLineDao;
import com.mocha.cooperate.basic.dao.impl.NotifyLineDaoImpl;
import com.mocha.cooperate.service.NotifyLineService;

/**
 * @author Coral
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class NotifyServiceTest {

	@Test
	public void testLoadNotifyNumber() {
		BasicUserService userService = new BasicUserService();
		BasicUser basicUser = userService.findById(Long.valueOf(1));
		
		NotifyLineService ns = new NotifyLineService();
		System.out.println(ns.loadNotifyNumber(basicUser));
		
		ns.readAllNotify(basicUser);
		
	}
}
