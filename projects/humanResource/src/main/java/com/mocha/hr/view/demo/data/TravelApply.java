package com.mocha.hr.view.demo.data;

import java.util.Date;

public class TravelApply {

	public String destination;
	public Long duration;
	public Date departureDate;
	public Date returnDate;
	public String travelType;
	public String remark;
	
	public TravelApply(String destination, Long duration, Date departureDate, Date returnDate) {
		this.destination = destination;
		this.duration = duration;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}
	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	/**
	 * @return the travelType
	 */
	public String getTravelType() {
		return travelType;
	}
	/**
	 * @param travelType the travelType to set
	 */
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
