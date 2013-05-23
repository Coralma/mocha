package com.homepage.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;

public class AccountFeeDao extends abstractDao {

	public AccountFeeDao() {
		super();
	}

	public void createAccountFee(AccountFee accountFee) {
		entityMfg.getTransaction().begin();
		entityMfg.persist(accountFee);
		entityMfg.getTransaction().commit();
		closeEntityMfg();
	}

	public AccountFee findLatestAccountFee(User user) {
		Query findAccountFee = entityMfg
				.createNamedQuery("findAccountFeesByUserEmailaddress");
		findAccountFee.setParameter("emailAddress", user.getEmailAddress());
		List<AccountFee> returnAccountFees = findAccountFee.getResultList();
		AccountFee returnAccountFee = new AccountFee();
		for (AccountFee accountFee : returnAccountFees) {
			if (returnAccountFee == null
					|| accountFee.getAccountFeeId() > returnAccountFee
							.getAccountFeeId()) {
				returnAccountFee = accountFee;
			}
		}

		// java.util.Date returnDate = null;
		// for (AccountFee accountFee : accountFees) {
		// if (returnAccountFee == null
		// || returnAccountFee.getExpireDate().before(
		// accountFee.getExpireDate())) {
		// returnAccountFee = accountFee;
		// }
		// }
		return returnAccountFee;
	}

	public void createAccountFeeByOrder(Order order) {
		AccountFee accountFee = new AccountFee();
		java.util.Date currentDate = new java.util.Date();
		Date startDate = new Date(currentDate.getTime());
		Date expireDate = new Date(startDate.getTime());
		expireDate.setYear(startDate.getYear() + 1);
		accountFee.setType(order.getOrderType());
		accountFee.setStartDate(startDate);
		accountFee.setExpireDate(expireDate);
		accountFee.setAccount(order.getAccount());
		entityMfg.getTransaction().begin();
		UserDao userDao = new UserDao();
		User user = userDao.findUserByEmail(order.getUser().getEmailAddress());
		accountFee.setUser(user);
		user.getAccountFees().add(accountFee);
		user.setAccount(order.getAccount());
		entityMfg.merge(user);
		entityMfg.getTransaction().commit();
	}

}
