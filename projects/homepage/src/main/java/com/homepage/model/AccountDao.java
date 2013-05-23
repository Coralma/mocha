package com.homepage.model;

import javax.persistence.Query;

public class AccountDao extends abstractDao {

	public AccountDao() {
		super();
	}

	public Account getAccountByUser(User user) {
		try {
			entityMfg.getTransaction().begin();
			Account account = new Account();
			Query findAccountQuery = entityMfg
					.createNamedQuery("findAccountByUserid");
			findAccountQuery.setParameter("userId", user.getId());
			account = (Account) findAccountQuery.getSingleResult();
			entityMfg.getTransaction().commit();
			return account;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
