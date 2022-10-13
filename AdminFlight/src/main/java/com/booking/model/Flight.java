package com.booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes="Unique id for the flight")
	private int flightId;
	@ApiModelProperty(notes="Destination of the Flight")
	private String arrivalLoc;
	@ApiModelProperty(notes="Origin of the flight")
	private String departureLoc;
	@ApiModelProperty(notes="Name of the flight")
	private String fleetName;
	@ApiModelProperty(notes="Model of the flight")
	private String model;
	@ApiModelProperty(notes="Remaining seats available in the flight")
	private Integer remainingSeats;
	@ApiModelProperty(notes="Departure date of the flight")
	private String date;

	public Flight() {
		super();
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", arrivalLoc=" + arrivalLoc + ", departureLoc=" + departureLoc
				+ ", fleetName=" + fleetName + ", model=" + model + ", remainingSeats=" + remainingSeats + ", date="
				+ date + "]";
	}

	public Flight(int flightId, String arrivalLoc, String departureLoc, String fleetName, String model,
			Integer remainingSeats,String date) {
		super();
		this.flightId = flightId;
		this.arrivalLoc = arrivalLoc;
		this.departureLoc = departureLoc;
		this.fleetName = fleetName;
		this.model = model;
		this.remainingSeats = remainingSeats;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getArrivalLoc() {
		return arrivalLoc;
	}

	public void setArrivalLoc(String arrivalLoc) {
		this.arrivalLoc = arrivalLoc;
	}

	public String getDepartureLoc() {
		return departureLoc;
	}

	public void setDepartureLoc(String departureLoc) {
		this.departureLoc = departureLoc;
	}

	public String getFleetName() {
		return fleetName;
	}

	public void setFleetName(String fleetName) {
		this.fleetName = fleetName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(Integer remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
	
}
