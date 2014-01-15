package com.mocha.hr.view.demo.data;

import java.math.BigDecimal;

import com.coral.foundation.security.model.BasicUser;

public class LeaveManage {

	public String leaveType;
	public BigDecimal assigned;
	public BigDecimal remainingBalance;
	public BasicUser user;
	
	public LeaveManage(String leaveType, BigDecimal assigned, BigDecimal remainingBalance) {
		this.leaveType = leaveType;
		this.assigned = assigned;
		this.remainingBalance = remainingBalance;
	}
	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	/**
	 * @return the assigned
	 */
	public BigDecimal getAssigned() {
		return assigned;
	}
	/**
	 * @param assigned the assigned to set
	 */
	public void setAssigned(BigDecimal assigned) {
		this.assigned = assigned;
	}
	/**
	 * @return the remainingBalance
	 */
	public BigDecimal getRemainingBalance() {
		return remainingBalance;
	}
	/**
	 * @param remainingBalance the remainingBalance to set
	 */
	public void setRemainingBalance(BigDecimal remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	/**
	 * @return the user
	 */
	public BasicUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(BasicUser user) {
		this.user = user;
	}
	
}
