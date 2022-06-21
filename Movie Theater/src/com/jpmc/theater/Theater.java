package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.jpmc.theater.exceptions.InvalidMovieSequenceException;
import com.jpmc.theater.exceptions.InvalidShowStartTimeException;

/**
 * 
 * @author roopsatsangi
 *
 */
public class Theater {

	private LocalDate date;
	private List<Showing> movieSchedule = new ArrayList<Showing>();
	
	public Theater(LocalDate date) {
		this.date = date;
	}

	/**
	 * Initialize Movie Schedule for demo purposes
	 */
	public void initializeDemoMovieSchedule() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12.5, true);
		Movie turningRed = new Movie("Turning Red", "", Duration.ofMinutes(85), 11, false);
		Movie theBatMan = new Movie("The Batman", "", Duration.ofMinutes(95), 9, false);
		
		movieSchedule = List.of(
				new Showing(turningRed, 1, LocalDateTime.of(date, LocalTime.of(9, 0))),
				new Showing(spiderMan, 2, LocalDateTime.of(date, LocalTime.of(11, 0))),
				new Showing(theBatMan, 3, LocalDateTime.of(date, LocalTime.of(12, 50))),
				new Showing(turningRed, 4, LocalDateTime.of(date, LocalTime.of(14, 30))),
				new Showing(spiderMan, 5, LocalDateTime.of(date, LocalTime.of(16, 10))),
				new Showing(theBatMan, 6, LocalDateTime.of(date, LocalTime.of(17, 50))),
				new Showing(turningRed, 7, LocalDateTime.of(date, LocalTime.of(19, 30))),
				new Showing(spiderMan, 8, LocalDateTime.of(date, LocalTime.of(21, 10))),
				new Showing(theBatMan, 9, LocalDateTime.of(date, LocalTime.of(23, 0)))
				);
	}
	
	public void addShowingToMovieSchedule(Showing showing) throws InvalidShowStartTimeException {
		if (showing.getShowStartTime().toLocalDate().isEqual(date)) {
			movieSchedule.add(showing);
			
		} else {
			throw new InvalidShowStartTimeException("Invalid show start time/ date: " + showing.getShowStartTime());
		}
	}
	
	public Reservation makeCustomerReservation(Customer customer, int movieSequence, int numberOfTickets) throws InvalidMovieSequenceException {
		Showing showing;

		try {
			showing = movieSchedule.get(movieSequence - 1);

		} catch (Exception e) {
			throw new InvalidMovieSequenceException("Unable to find any showing for the given movie sequence " + movieSequence);
		}

		return new Reservation(customer, showing, numberOfTickets);
	}

	public void printScheduleInSimpleTextFormat() {
		System.out.println(date);

		System.out.println("===================================================");
		movieSchedule.forEach(s ->
		System.out.println(s.getSequenceOfTheDay() + ": " 
				+ s.getShowStartTime() + " " 
				+ s.getMovie().getTitle() + " " 
				+ humanReadableFormat(s.getMovie().getRunningTime()) 
				+ " $" + s.getShowPrice())
				);
		System.out.println("===================================================");
	}

	public String humanReadableFormat(Duration duration) {
		long hour = duration.toHours();
		long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

		return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
	}

	private String handlePlural(long value) {
		if (value == 1) {
			return "";
		} else {
			return "s";
		}
	}

	public void printScheduleInJSONFormat() {
		System.out.println("{");
		System.out.println("\"date\": \"" + date + "\",");
		System.out.println("\"showings\": [");

		Iterator<Showing> itr = movieSchedule.iterator();
		while(itr.hasNext()) {
			Showing s = itr.next();

			System.out.println("{");
			System.out.println("\"sequenceOfTheDay\": " + s.getSequenceOfTheDay() + ",");
			System.out.println("\"showStartTime\": \"" + s.getShowStartTime() + "\",");
			System.out.println("\"movie\": \"" + s.getMovie().getTitle() + "\",");
			System.out.println("\"runningTimeInMinutes\": " + s.getMovie().getRunningTime().toMinutes() + ",");
			System.out.println("\"showPrice\": \"$" + s.getShowPrice() + "\"");

			if (itr.hasNext()) {
				System.out.println("},");
			} else {
				System.out.println("}");
			}
		}

		System.out.println("]");
		System.out.println("}");
	}
	
	
	public LocalDate getDate() {
		return date;
	}

	public List<Showing> getMovieSchedule() {
		return movieSchedule;
	}

	public static void main(String[] args) {
		Theater theater = new Theater(LocalDate.now());
		theater.initializeDemoMovieSchedule();

		theater.printScheduleInSimpleTextFormat();
		theater.printScheduleInJSONFormat();
	}
}
