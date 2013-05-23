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
@Table(name = "t_order")
@NamedQueries({ @NamedQuery(name = "updateOrderByOrderOID", query = "Update Order o set o.orderStatus=:orderStatus WHERE o.orderOID=:orderOID"),
	@NamedQuery(name="findOrderByOrderOID",query="Select o from Order o where o.orderOID=:orderOID")})
public class Order extends Beans implements Serializable {

	@Id()
	@Column(name = "orderId")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Basic(optional = true)
	@Column(name = "name")
	private String name;

	@Basic(optional = true)
	@Column(name = "orderOID")
	private String orderOID;

	@Basic(optional = true)
	@Column(name = "createdDate")
	private java.sql.Date createdDate;

	@Basic(optional = true)
	@Column(name = "orderStatus")
	private String orderStatus;

	@ManyToOne(fetch = FetchType.EAGER,optional=false,cascade=CascadeType.ALL)
//	@JoinColumn(name = "userId",insertable=false)
//	@JoinFetch(JoinFetchType.INNER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER,optional=false,cascade=CascadeType.ALL)
//	@JoinColumn(name = "accountId",insertable=false)
//	@JoinFetch(JoinFetchType.INNER)
	private Account account;
	
	@Column(name="orderType")
	private AccountFeeType orderType;

	public Order() {

	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public java.sql.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.sql.Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getOrderOID() {
		return orderOID;
	}

	public void setOrderOID(String orderOID) {
		this.orderOID = orderOID;
	}

	public AccountFeeType getOrderType() {
		return orderType;
	}

	public void setOrderType(AccountFeeType orderType) {
		this.orderType = orderType;
	}

}
