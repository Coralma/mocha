package com.homepage.model;

import java.beans.Beans;
import java.io.Serializable;
import java.util.ArrayList;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.homepage.payment.AccountFeeType;

@Entity
@Table(name = "t_account")
@NamedQueries({@NamedQuery(name = "findAccountByUserid", query = "SELECT a FROM Account a join a.users u where u.id = :userId")})
public class Account extends Beans implements Serializable {
	@Id()
	@Column(name = "accountId")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Basic(optional = true)
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Basic(optional = true)
	@Column(name = "type")
	private AccountFeeType type;

	@Basic(optional = true)
	@Column(name = "accountType", nullable = false)
	private String accountType;

	@Basic(optional = true)
	@Column(name = "accountSize",  nullable = false)
	private String accountSize;

	@Basic(optional = true)
	@Column(name = "accountAddress",  nullable = false)
	private String accountAddress;

	@Basic(optional = true)
	@Column(name = "accountPhone",  nullable = false)
	private String accountPhone;

	@Basic(optional = true)
	@Column(name = "twitterAccount",  nullable = false)
	private String twitterAccount;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private ArrayList<User> users = new ArrayList<User>();

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private ArrayList<AccountFee> accountFees = new ArrayList<AccountFee>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Order> orders = new ArrayList<Order>();

	public Account() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountFeeType getType() {
		return type;
	}

	public void setType(AccountFeeType type) {
		this.type = type;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<AccountFee> getAccountFees() {
		return accountFees;
	}

	public void setAccountFees(ArrayList<AccountFee> accountFees) {
		this.accountFees = accountFees;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountSize() {
		return accountSize;
	}

	public void setAccountSize(String accountSize) {
		this.accountSize = accountSize;
	}

	public String getTwitterAccount() {
		return twitterAccount;
	}

	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

}
