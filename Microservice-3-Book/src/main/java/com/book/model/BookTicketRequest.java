package com.book.model;

import java.util.HashMap;
import java.util.List;

public class BookTicketRequest {
	
	private String tripId;
    private String tripDate;
    private String trainId;
    private HashMap<String, Integer> seats;
    private List<ModelPassenger> passengers;
    private String email;

    @Override
    public String toString() {
        return "BookTicketRequest{" +
                "tripId='" + tripId + '\'' +
                ", tripDate=" + tripDate +
                ", trainId='" + trainId + '\'' +
                ", seats=" + seats +
                ", email=" + email +
                ", passengers=" + passengers +
                '}';
    }

    
    
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public HashMap<String, Integer> getSeats() {
		return seats;
	}

	public void setSeats(HashMap<String, Integer> seats) {
		this.seats = seats;
	}

	public List<ModelPassenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<ModelPassenger> passengers) {
		this.passengers = passengers;
	}



	public BookTicketRequest(String tripId, String tripDate, String trainId, HashMap<String, Integer> seats,
			List<ModelPassenger> passengers, String email) {
		super();
		this.tripId = tripId;
		this.tripDate = tripDate;
		this.trainId = trainId;
		this.seats = seats;
		this.passengers = passengers;
		this.email = email;
	}

	public BookTicketRequest() {
		
	}
}
