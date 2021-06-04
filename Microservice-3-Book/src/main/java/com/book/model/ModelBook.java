package com.book.model;

import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("reserve")
public class ModelBook {

	@Id
	private String ticketId;
	@Indexed

	private String trainId;
	private String name;
	private int age;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String seatType;
	
	public ModelBook() {
		
	}
		
	public ModelBook(String trainId, String name, int age, String email, LocalDate date,
			String seatType) {
		super();
	
		this.trainId = trainId;
		this.name = name;
		this.age = age;
		this.email = email;
		this.date = date;
		this.seatType = seatType;
	}


	public String getEmail() {
		return email;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return ticketId;
	}
	public void setId(String id) {
		ticketId = id;
	}

	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
