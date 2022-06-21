package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.exceptions.InvalidMovieSequenceException;
import com.jpmc.theater.exceptions.InvalidShowStartTimeException;

/**
 * 
 * @author roopsatsangi
 *
 */
class TheaterTests {
	
	public static Theater theater;
	
	public static Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12.5, true);
	public static Movie turningRed = new Movie("Turning Red", "", Duration.ofMinutes(85), 11, false);
	public static Movie theBatMan = new Movie("The Batman", "", Duration.ofMinutes(95), 9, false);
	
	public static Customer sam = new Customer("Sam Smith", "id-431");
	
	@BeforeAll
	public static void setUp() throws InvalidShowStartTimeException  {
		LocalDate date = LocalDate.now();
		theater = new Theater(date);
		
		theater.addShowingToMovieSchedule(new Showing(turningRed, 1, LocalDateTime.of(date, LocalTime.of(9, 0))));
		theater.addShowingToMovieSchedule(new Showing(spiderMan, 2, LocalDateTime.of(date, LocalTime.of(11, 0))));
		theater.addShowingToMovieSchedule(new Showing(theBatMan, 3, LocalDateTime.of(date, LocalTime.of(12, 50))));
		theater.addShowingToMovieSchedule(new Showing(turningRed, 4, LocalDateTime.of(date, LocalTime.of(14, 30))));
		theater.addShowingToMovieSchedule(new Showing(spiderMan, 5, LocalDateTime.of(date, LocalTime.of(16, 10))));
		theater.addShowingToMovieSchedule(new Showing(theBatMan, 6, LocalDateTime.of(date, LocalTime.of(17, 50))));
		theater.addShowingToMovieSchedule(new Showing(turningRed, 7, LocalDateTime.of(date, LocalTime.of(19, 30))));
		theater.addShowingToMovieSchedule(new Showing(spiderMan, 8, LocalDateTime.of(date, LocalTime.of(21, 10))));
		theater.addShowingToMovieSchedule(new Showing(theBatMan, 9, LocalDateTime.of(date, LocalTime.of(23, 0))));
	}
	
	@Test
	void testAddShowingToMovieSchedule() throws InvalidShowStartTimeException {
		
		assertThrows(InvalidShowStartTimeException.class, () -> {
			theater.addShowingToMovieSchedule(new Showing(turningRed, 10, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(7, 0))));
		});
	}

	@Test
	void testCalculateReservationFee1() throws InvalidMovieSequenceException {
		Reservation reservation = theater.makeCustomerReservation(sam, 1, 5);
		
		/**
		 * Reservation Fee = Show Price * Audience Count = 8 * 5 = 40
		 */
		assertEquals(40, reservation.calculateReservationFee());
	}
	
	@Test
	void testCalculateReservationFee2() throws InvalidMovieSequenceException {
		Reservation reservation = theater.makeCustomerReservation(sam, 5, 3);
		
		/**
		 * Reservation Fee = Show Price * Audience Count = 10 * 3 = 30
		 */
		assertEquals(30, reservation.calculateReservationFee());
	}
	
	@Test
	void testCalculateReservationFee3() throws InvalidMovieSequenceException {
		Reservation reservation = theater.makeCustomerReservation(sam, 9, 6);
		
		/**
		 * Reservation Fee = Show Price * Audience Count = 9 * 6 = 54
		 */
		assertEquals(54, reservation.calculateReservationFee());
	}
	
	@Test
	void testMakeCustomerReservation() throws InvalidMovieSequenceException {
		
		assertThrows(InvalidMovieSequenceException.class, () -> {
			theater.makeCustomerReservation(sam, 10, 6);
		});
	}
}
