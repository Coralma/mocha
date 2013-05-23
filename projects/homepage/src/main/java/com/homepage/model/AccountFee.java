package com.homepage.model;

import java.beans.Beans;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.homepage.payment.AccountFeeType;

@Entity
@Table(name = "t_accountFees")
@NamedQueries({ @NamedQuery(name = "findAccountFeesByUserEmailaddress", query = "SELECT a FROM AccountFee a join a.user u WHERE u.emailAddress = :emailAddress "
		+ "order by a.expireDate desc") })
public class AccountFee extends Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(name = "accountFeeId")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long accountFeeId;

	@Basic(optional = true)
	@Column(name = "name")
	private String name;

	@Basic(optional = true)
	@Column(name = "type")
	private AccountFeeType type;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	@JoinFetch(JoinFetchType.INNER)
	private User user;

	@Column(name = "startDate")
	private java.sql.Date startDate;

	@Column(name = "expireDate")
	private java.sql.Date expireDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "account", referencedColumnName = "accountId")
	@JoinFetch(JoinFetchType.INNER)
	private Account account = new Account();

	public AccountFee() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccountFeeType getType() {
		return type;
	}

	public void setType(AccountFeeType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountFeeId() {
		return accountFeeId;
	}

	public void setAccountFeeId(long accountFeeId) {
		this.accountFeeId = accountFeeId;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(java.sql.Date expireDate) {
		this.expireDate = expireDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
