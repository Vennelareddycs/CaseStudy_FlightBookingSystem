package com.capg.service;

import java.util.List;  
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.capg.Exception.BookingException;
import com.capg.Exception.ResourceNotFoundException;
import com.capg.model.EmailDetails;
import com.capg.model.Fare;
import com.capg.model.Flight;
import com.capg.model.FlightData;
import com.capg.model.Passenger;
import com.capg.repository.BookingRepository;
import com.capg.repository.FlightRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingRepository br;
	
	@Autowired
	FlightRepository fr;
	
	@Autowired
	private RestTemplate restTemplate;

	private static final String EMAIL_URL="http://EMAIL-MICROSERVICE/email/send-mail";
	private static final String FARE_URL="http://FARE-MICROSERVICE/fare";

	public String addBooking(FlightData flightdata) {
		Flight flight=restTemplate.getForObject("http://ADMIN-MICROSERVICE/flight/find/"+flightdata.getFlightNumber()+"/"+flightdata.getBookingDate(),Flight.class);
		if(flight.getRemainingSeats()<=br.count()) {
			throw new BookingException("No more seats available");
		}else {
		Fare fare = restTemplate.getForObject(FARE_URL+"/get?flightNumber="+flightdata.getFlightNumber()+"&flightDate="+flightdata.getBookingDate(),Fare.class);
		flightdata.setFare(fare.getFare());
		flightdata.setStatus(BookingStatus.BOOKING_CONFIRMED);
		flightdata.setOrigin(flight.getArrivalLoc());
		flightdata.setDestination(flight.getDepartureLoc());
		flightdata.setFlightNumber(flight.getModel());
		flightdata.setBookingDate(flight.getDate());
		fr.save(flightdata);
		String emailBody="Your Booking is Confirmed. Reference Number is "+flightdata.getPnr();
		String emailSubject = "Booking Confirmation";
		EmailDetails mail = new EmailDetails("vennela24gowda@gmail.com",emailBody,emailSubject);
		restTemplate.postForObject(EMAIL_URL, mail, String.class);
		return "Your Booking is Confirmed. Reference Number is "+flightdata.getPnr();
		}
	} 
	
	
	public List<FlightData> getBooking() {
		return fr.findAll();
	}
	
	public Optional<Passenger> getbyId(int id) {	
		return br.findById(id);
	}
	
	public Optional<FlightData> getBookingByPnr(int pnr) {
		try {
			return fr.findById(pnr);
		} catch (Exception e) {
			throw new ResourceNotFoundException("NO SUCH PNR");
		}
	}
	
	public FlightData updateBooking(FlightData f) {
		return fr.save(f);
	}
	
	public Passenger addDetails(Passenger p) { 
		  return br.save(p);
	} 
	
	public String deleteId(int id) {
		fr.deleteById(id);
		return "deleted";
	}
	
	public String deleteAll() {
		fr.deleteAll();
		return "All Bookings deleted";
	}

	public Optional<FlightData> getbypnr(int pnr) {
		return fr.findById(pnr);
	}
}
