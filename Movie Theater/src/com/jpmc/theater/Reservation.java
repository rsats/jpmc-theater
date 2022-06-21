package com.jpmc.theater;

/**
 * 
 * @author roopsatsangi
 *
 */
public class Reservation {
	private Customer customer;
	private Showing showing;
	private int audienceCount;
	
	public Reservation(Customer customer, Showing showing, int audienceCount) {
		super();
		this.customer = customer;
		this.showing = showing;
		this.audienceCount = audienceCount;
	}
	
	public double calculateReservationFee() {
		return showing.getShowPrice() * audienceCount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Showing getShowing() {
		return showing;
	}
	public void setShowing(Showing showing) {
		this.showing = showing;
	}
	public int getAudienceCount() {
		return audienceCount;
	}
	public void setAudienceCount(int audienceCount) {
		this.audienceCount = audienceCount;
	}
}
