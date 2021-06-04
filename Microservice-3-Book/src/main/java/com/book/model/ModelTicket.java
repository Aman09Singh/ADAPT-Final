package com.book.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("ticket")
public class ModelTicket {

	@Id
    @Indexed
    private String ticketId;
    private String PNR;
    private List<ModelPassenger> passengers;
    private Boolean cancellable;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate journeyDate;
    private String email;
    private String tripScheduleId;
    private HashMap<String, List<Integer>> seats;
    private String status;
    private String fromStationCode;
    private String toStationCode;
    
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", PNR='" + PNR + '\'' +
                ", passengers=" + passengers +
                ", cancellable=" + cancellable +
                ", journeyDate=" + journeyDate +
                ", email='" + email + '\'' +
                ", tripScheduleId='" + tripScheduleId + '\'' +
                ", seats=" + seats +
                ", status='" + status + '\'' +
                '}';
    }

  public ModelTicket() {
	  
  }

	public ModelTicket(String ticketId, String pNR, List<ModelPassenger> passengers, Boolean cancellable,
			LocalDate journeyDate, String email, String tripScheduleId, HashMap<String, List<Integer>> seats,
			String status, String fromStationCode, String toStationCode) {
		super();
		this.ticketId = ticketId;
		PNR = pNR;
		this.passengers = passengers;
		this.cancellable = cancellable;
		this.journeyDate = journeyDate;
		this.email = email;
		this.tripScheduleId = tripScheduleId;
		this.seats = seats;
		this.status = status;
		this.fromStationCode = fromStationCode;
		this.toStationCode = toStationCode;
	}



	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getPNR() {
		return PNR;
	}

	public void setPNR(String pNR) {
		PNR = pNR;
	}

	public List<ModelPassenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<ModelPassenger> passengers) {
		this.passengers = passengers;
	}

	public Boolean getCancellable() {
		return cancellable;
	}

	public void setCancellable(Boolean cancellable) {
		this.cancellable = cancellable;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getTripScheduleId() {
		return tripScheduleId;
	}

	public void setTripScheduleId(String tripScheduleId) {
		this.tripScheduleId = tripScheduleId;
	}

	public HashMap<String, List<Integer>> getSeats() {
		return seats;
	}

	public void setSeats(HashMap<String, List<Integer>> seats) {
		this.seats = seats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromStationCode() {
		return fromStationCode;
	}

	public void setFromStationCode(String fromStationCode) {
		this.fromStationCode = fromStationCode;
	}

	public String getToStationCode() {
		return toStationCode;
	}

	public void setToStationCode(String toStationCode) {
		this.toStationCode = toStationCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
