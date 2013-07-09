package com.mocha.cooperate.basic.dao.impl;
import javax.persistence.Query;

import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.model.BasicUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
  * NotifyLineDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class NotifyLineDaoImpl extends JpaDao<NotifyLine> implements NotifyLineDao {
	
	Logger log=LoggerFactory.getLogger(NotifyLineDaoImpl.class);
	public NotifyLineDaoImpl() {
		super();
		log.debug(""+NotifyLineDaoImpl.class);
	}

	@Override
	public int loadNotifyNumber(BasicUser basicUser) {
		Query query = entityManager.createQuery("Select count(n) from NotifyLine as n where type='1' and notifiedUser = :notifiedUser");
		query.setParameter("notifiedUser", basicUser);
		Long count = (Long)query.getSingleResult();
		return count.intValue();
	}
	
	@Transactional
	public void readAllNotify(BasicUser basicUser) {
		try {
			Query query = entityManager.createQuery("UPDATE NotifyLine t SET t.type='0' where t.type='1' and t.notifiedUser = :notifiedUser");
			query.setParameter("notifiedUser", basicUser);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

