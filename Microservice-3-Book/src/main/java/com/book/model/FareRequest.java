package com.book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fare")
public class FareRequest {

	@Id
	private String fareId;
	
	private String email;
	private String bookingId;
	private long fare;
	
	public FareRequest() {
		
	}
	
	public FareRequest(String fareId, String email, String bookingId, long fare) {
		super();
		this.fareId = fareId;
		this.email = email;
		this.bookingId = bookingId;
		this.fare = fare;
	}

	public String getFareId() {
		return fareId;
	}

	public void setFareId(String fareId) {
		this.fareId = fareId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}
	
	
	
}
