package com.mocha.hr.view.demo.data;

import java.util.Date;

import com.coral.foundation.utils.DateUtils;

public class ReimbursementApply {

	private Date reimbursementDate;
	private String totalAmount;
	private String currency;
	private String chargeDepartment;
	private String approver;
	private Date processingDate;

	public ReimbursementApply(Date reimbursementDate, String totalAmount, String currency, String chargeDepartment, String approver) {
		this.reimbursementDate = reimbursementDate;
		this.totalAmount = totalAmount;
		this.currency = currency;
		this.chargeDepartment = chargeDepartment;
		this.approver = approver;
	}
	/**
	 * @return the reimbursementDate
	 */
	public Date getReimbursementDate() {
		return reimbursementDate;
	}
	/**
	 * @param reimbursementDate the reimbursementDate to set
	 */
	public void setReimbursementDate(Date reimbursementDate) {
		this.reimbursementDate = reimbursementDate;
	}
	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the chargeDepartment
	 */
	public String getChargeDepartment() {
		return chargeDepartment;
	}
	/**
	 * @param chargeDepartment the chargeDepartment to set
	 */
	public void setChargeDepartment(String chargeDepartment) {
		this.chargeDepartment = chargeDepartment;
	}
	/**
	 * @return the approver
	 */
	public String getApprover() {
		return approver;
	}
	/**
	 * @param approver the approver to set
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}
	/**
	 * @return the processingDate
	 */
	public Date getProcessingDate() {
		if(processingDate == null) {
			return DateUtils.addDay(reimbursementDate,12);
		}
		return processingDate;
	}
	/**
	 * @param processingDate the processingDate to set
	 */
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}
}
