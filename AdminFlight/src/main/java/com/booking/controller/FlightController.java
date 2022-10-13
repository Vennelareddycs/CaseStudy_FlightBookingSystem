package com.booking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.Exceptions.NoDataFoundException;
import com.booking.Exceptions.NoSuchFlightIdException;
import com.booking.model.Flight;
import com.booking.service.FlightService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	FlightService flightService;
	
	@PostMapping("/add-one-flight")
	@ApiOperation(value="Add new FlightDetails",response=Flight.class)
	public String addOneFlight(
			@RequestBody Flight flight){
		return flightService.addOneFlight(flight);
	}
	
	@GetMapping("/get-all-flights")
	@ApiOperation(value="Get the List of FlightDetails available",response=Flight.class)
	public List<Flight> getAllFlights(){
		if(flightService.getAllFlightDetails().isEmpty()) {
			throw new NoDataFoundException("There are no records to view the flight details.");
		}
		return flightService.getAllFlightDetails();
	}
	
	@GetMapping("/get-flight-by-id/{flightId}")
	@ApiOperation(value="Get the List of FlightDetails available based on flightId",response=Flight.class)
	public Flight getFlightById(
			@PathVariable("flightId") int flightId){
		Flight flight=flightService.getFlightByFlightId(flightId);
		if(flight==null) {
			throw new NoSuchFlightIdException("There is no flight with flight ID "+flightId);
		}
		return flight;
	}
	
	@GetMapping("/find/{model}/{date}")
	@ApiOperation(value="Search for the FlightDetails based on model and date",response=Flight.class)
	public Flight getFlight(@PathVariable String model,@PathVariable String date) {
		return flightService.findByModelandDate(model,date);
	}
	
	@GetMapping("/findbyData/{date}/{arrivalLoc}/{departureLoc}")
	@ApiOperation(value="Search for the FlightDetails based on date, arrival and departure location",response=Flight.class)
	public List<Flight> getFlightByDateAndArrivalLocAndDepartureLoc(
			@PathVariable String date,@PathVariable String arrivalLoc,@PathVariable String departureLoc){
		List<Flight> flight=flightService.findByDateAndArrivalLocAndDepartureLoc(date,arrivalLoc,departureLoc);	
		if(flight==null) {
			throw new NoDataFoundException("There are no records with this data.");
		}
		return flight;	
	}
	
	@GetMapping("/findbyDate/{date}")
	@ApiOperation(value="Search for the FlightDetails based on date",response=Flight.class)
	public List<Flight> getFlightByDate(
			@PathVariable String date){
		List<Flight> flight= flightService.findByDate(date);	
		if(flight==null) {
			throw new NoDataFoundException("No records with this date.");
		}
		return flight;	
	}
	
	@PutMapping("/update-flight")
	@ApiOperation(value="Update the FlightDetails",response=Flight.class)
	public Flight updateFlight(
			@RequestBody Flight flight){
		return flightService.updateFlightDetail(flight);
	}
	
	
	@DeleteMapping("/delete-flight-by-id/{flightId}")
	@ApiOperation(value="Delete the FlightDetails by flightId",response=Flight.class)
	public String deleteFlightByid(
			@PathVariable("flightId") int flightId){
		Flight flight=flightService.getFlightByFlightId(flightId);
		if(flight==null) {
			throw new NoSuchFlightIdException("There is no flight with flight ID "+flightId);
		}
		return flightService.deleteByFlightId(flightId);
	}
	
	@DeleteMapping("/delete-all-flights")
	@ApiOperation(value="Delete all the FlightDetails",response=Flight.class)
	public String deleteAllFlights(){
		return flightService.deleteAllFlightDetails();
	}
	
	
}
