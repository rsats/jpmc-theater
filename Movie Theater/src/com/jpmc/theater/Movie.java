package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

/**
 * 
 * @author roopsatsangi
 *
 */
public class Movie {
	private String title;
	private String description;
	private Duration runningTime;
	private double ticketPrice;
	private boolean isSpecial;
	
	public Movie(String title, String description, Duration runningTime, double ticketPrice, boolean isSpecial) {
		super();
		this.title = title;
		this.description = description;
		this.runningTime = runningTime;
		this.ticketPrice = ticketPrice;
		this.isSpecial = isSpecial;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Duration getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(Duration runningTime) {
		this.runningTime = runningTime;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public boolean isSpecial() {
		return isSpecial;
	}
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(description, isSpecial, runningTime, ticketPrice, title);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(description, other.description) && isSpecial == other.isSpecial
				&& Objects.equals(runningTime, other.runningTime)
				&& Double.doubleToLongBits(ticketPrice) == Double.doubleToLongBits(other.ticketPrice)
				&& Objects.equals(title, other.title);
	}
}
