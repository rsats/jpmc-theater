package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author roopsatsangi
 *
 */
class ShowingTests {

	@Test
	void testShowPriceSpecialMovie() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, true);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
		
		/**
		 * 20% discount on special movie. Show Price = 12 - 2.4 = 9.6
		 */
		assertEquals(9.6, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceFirstShowing() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
		
		/**
		 * $3 discount for the movie showing 1st of the day. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceSecondShowing() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
		
		/**
		 * $2 discount for the movie showing 2nd of the day. Show Price = 12 - 2 = 10
		 */
		assertEquals(10, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceSeventhShowing() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
		
		/**
		 * $1 discount for the movie showing 7th of the day. Show Price = 12 - 1 = 11
		 */
		assertEquals(11, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount1() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount2() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30)));
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount3() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 45)));
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount4() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 20)));
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount5() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 59)));
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount6() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
		
		/**
		 * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount. Show Price = 12 - 3 = 9
		 */
		assertEquals(9, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount7() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 1)));
		
		/**
		 * Edge Case. No discount for movie show starting at 4:01 PM
		 */
		assertEquals(12, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceStartTimeDiscount8() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12, false);
		Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 59)));
		
		/**
		 * Edge Case. No discount for movie show starting at 10:59 AM
		 */
		assertEquals(12, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceBiggestDiscountRule1() {
		/**
		 * Special Movie Discount 20% = 14 * .2 = 2.8
		 */
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 14, true);
		
		/** 
		 * 1st Showing Discount = $3
		 * 11 AM Start Time Discount 25% = 14 * .25 = $3.5
		 */
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
		
		/**
		 * Biggest discount wins = $3.5. Show Price = 14 - 3.5 = 10.5
		 */
		assertEquals(10.5, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceBiggestDiscountRule2() {
		/**
		 * Special Movie Discount 20% = 14 * .2 = 2.8
		 */
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 14, true);
		
		/** 
		 * 1st Showing Discount = $3
		 */
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
		
		/**
		 * Biggest discount wins = $3. Show Price = 14 - 3 = 11
		 */
		assertEquals(11, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceBiggestDiscountRule3() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 14, false);
		
		/** 
		 * 1st Showing Discount = $3
		 * 11 AM Start Time Discount 25% = 14 * .25 = $3.5
		 */
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
		
		/**
		 * Biggest discount wins = $3.5. Show Price = 14 - 3.5 = 10.5
		 */
		assertEquals(10.5, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceBiggestDiscountRule4() {
		/**
		 * Special Movie Discount 20% = 14 * .2 = 2.8
		 */
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 14, true);
		
		/** 
		 * 2nd Showing Discount = $2
		 */
		Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
		
		/**
		 * Biggest discount wins = $2.8. Show Price = 14 - 2.8 = 11.2
		 */
		assertEquals(11.2, showing.getShowPrice());
	}
	
	@Test
	void testShowPriceBiggestDiscountRule5() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 10, true);
		
		/** 
		 * 1st Showing Discount = $3
		 * 11 AM Start Time Discount 25% = 10 * .25 = $2.5
		 */
		Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
		
		/**
		 * Biggest discount wins = $3. Show Price = 10 - 3 = 7
		 */
		assertEquals(7, showing.getShowPrice());
	}
}
