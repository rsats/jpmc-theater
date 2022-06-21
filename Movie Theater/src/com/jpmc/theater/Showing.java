package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * 
 * @author roopsatsangi
 *
 */
public class Showing {
	private Movie movie;
	private int sequenceOfTheDay;
	private LocalDateTime showStartTime;
	
	public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
		super();
		this.movie = movie;
		this.sequenceOfTheDay = sequenceOfTheDay;
		this.showStartTime = showStartTime;
	}
	
	public double getShowPrice() {
		return movie.getTicketPrice() - getShowDiscount();
	}
	
	private double getShowDiscount() {
		double specialDiscount = 0;
		double sequenceDiscount = 0;
		double showStartTimeDiscount = 0;
		
		/**
		 * 20% discount for the special movie
		 */
		if (movie.isSpecial()) {
			specialDiscount = movie.getTicketPrice() * 0.2;
		}
		
		/**
		 * $3 discount for the movie showing 1st of the day
		 * $2 discount for the movie showing 2nd of the day
		 * Any movies showing on 7th, you'll get 1$ discount
		 */
		if (sequenceOfTheDay == 1) {
			sequenceDiscount = 3;
		} else if (sequenceOfTheDay == 2) {
			sequenceDiscount = 2;
		} else if (sequenceOfTheDay == 7) {
			sequenceDiscount = 1;
		}
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
		 */
		if (showStartTime.toLocalTime().isAfter(LocalTime.of(10, 59)) && 
				showStartTime.toLocalTime().isBefore(LocalTime.of(16, 1))) {
			showStartTimeDiscount = movie.getTicketPrice() * 0.25;
		}
		
		/**
		 * Biggest discount wins
		 */
		double discounts[] = {specialDiscount, sequenceDiscount, showStartTimeDiscount};
		Arrays.sort(discounts);
		
		return discounts[discounts.length - 1];
	}
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getSequenceOfTheDay() {
		return sequenceOfTheDay;
	}
	public void setSequenceOfTheDay(int sequenceOfTheDay) {
		this.sequenceOfTheDay = sequenceOfTheDay;
	}
	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}
	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}
}
