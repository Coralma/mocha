package com.homepage.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class UserDao extends abstractDao {

	// @PersistenceContext(unitName = "homepage", type =
	// PersistenceContextType.EXTENDED)
	// protected EntityManager entityMfg;

	public UserDao() {
		super();
	}

	public Long createUserByAccount(Account account, User user, AccountFee accountFee) {
		entityMfg.getTransaction().begin();
		accountFee.setAccount(account);
		accountFee.setUser(user);
		entityMfg.persist(accountFee);
		account.getAccountFees().add(accountFee);
		account.getUsers().add(user);
		entityMfg.persist(account);
		user.getAccountFees().add(accountFee);
		user.setAccount(account);
		entityMfg.persist(user);
		// accountFee.setAccount(account);
		// accountFee.setUser(user);
		entityMfg.getTransaction().commit();
		closeEntityMfg();
		return user.getId();
	}
	
	
	

	public User findUserByEmail(String emailAddress) {
		entityMfg.getTransaction().begin();
		Query findUserQuery = entityMfg.createNamedQuery("findUserByEmailaddress");
		findUserQuery.setParameter("emailAddress", emailAddress);
		User user;
		try {
			user = (User) findUserQuery.getSingleResult();
		}
		catch (Exception e) {
			System.out.println("Errors in UserDao");
			e.printStackTrace();
			return null;
		}
		closeEntityMfg();
		return user;
	}

	public User findUserAndPw(String emailAddress, String pw) {
		entityMfg.getTransaction().begin();
		Query findUserQuery = entityMfg.createNamedQuery("findUserByEmailAddresAndPw");
		findUserQuery.setParameter("emailAddress", emailAddress.trim());
		findUserQuery.setParameter("pw", pw.trim());
		User user;
		try {
			user = (User) findUserQuery.getSingleResult();
			closeEntityMfg();
			return user;
		}
		catch (Exception e) {
			System.out.println("Errors in UserDao");
			return null;
		}
	}
}
