package com.homepage.model;

import java.beans.Beans;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
@Table(name = "t_user")
@NamedQueries({
		@NamedQuery(name = "findUserByEmailaddress", query = "SELECT u FROM User u WHERE u.emailAddress = :emailAddress"),
		@NamedQuery(name = "findUserByEmailAddresAndPw", query = "SELECT u FROM User u WHERE u.emailAddress = :emailAddress and u.pw= :pw") })
public class User extends Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Basic(optional = false)
	@Column(name = "userName")
	private String userName;

	@Basic(optional = true)
	@Column(name = "pw")
	private String pw;

	@Basic(optional = true)
	@Column(name = "emailAddress", unique = true)
	public String emailAddress;

	@Temporal(TemporalType.DATE)
	@Basic(optional = true)
	@Column(name = "lastLoginDate")
	private java.util.Date lastLoginDate;

	@Temporal(TemporalType.DATE)
	@Basic(optional = true)
	@Column(name = "userLoginDate")
	private java.util.Date userLoginDate;

	@Basic(optional = true)
	@Column(name = "cooperateUrl")
	private String cooperateUrl;

	@Basic(optional = false)
	@Column(name = "paidAccountFlag")
	private boolean paidAccountFlag;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinFetch(JoinFetchType.INNER)
	private List<AccountFee> accountFees = new ArrayList<AccountFee>();

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Account account = new Account();

	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true,mappedBy="user")
	private ArrayList<Order> orders = new ArrayList<Order>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public java.util.Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(java.util.Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setUserLoginDate(Date userLoginDate) {
		this.userLoginDate = userLoginDate;
	}

	public java.util.Date getUserLoginDate() {
		return userLoginDate;
	}

	public void setUserLoginDate(java.util.Date userLoginDate) {
		this.userLoginDate = userLoginDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCooperateUrl() {
		return cooperateUrl;
	}

	public void setCooperateUrl(String cooperateUrl) {
		this.cooperateUrl = cooperateUrl;
	}

	public boolean isPaidAccountFlag() {
		return paidAccountFlag;
	}

	public void setPaidAccountFlag(boolean paidAccountFlag) {
		this.paidAccountFlag = paidAccountFlag;
	}

	public boolean hasRole() {
		// TODO Auto-generated method stub
		return true;
	}

	public List<AccountFee> getAccountFees() {
		return accountFees;
	}

	public void setAccountFees(ArrayList<AccountFee> accountFees) {
		this.accountFees = accountFees;
	}

	public void setAccountFees(List<AccountFee> accountFees) {
		this.accountFees = accountFees;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

}
