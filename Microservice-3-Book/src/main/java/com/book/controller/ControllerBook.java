package com.book.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.exception.ApiRequestException;
import com.book.model.FareRequest;
import com.book.model.ModelBook;
import com.book.service.ServiceBooking;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/reserve")
public class ControllerBook {

	@Autowired
	ServiceBooking bookService;
	
	String PNR,cost,sType,email,BId;
	long seatcost,fare;
	int age;
	
	
	String FAC, TAC, SAC, FC, CC, SL;
	
	@PostMapping("/add")
	public ResponseEntity<ModelBook> reserveTicket(@RequestBody ModelBook book){
		
		bookService.reserveTicket(book);
		
		PNR = "PAKR"+book.getSeatType()+book.getId(); 
		
		sType = book.getSeatType();
		age = book.getAge();
		
		if(sType.equals("FAC")) {
			
			seatcost = 1400;
			
		}else if(sType.equals("SAC")) {
			
			seatcost = 1200;
			
		}else if(sType.equals("TAC")) {
			
			seatcost = 1000;
			
		}else if(sType.equals("FC")) {
			
			seatcost = 700;
			
		}else if(sType.equals("CC")) {
			
			seatcost = 600;
			
		}else if(sType.equals("SL")) {
			
			seatcost = 500;
			
		}
		
		fare = (long) (seatcost + age*0.75);
		email = book.getEmail();
		BId = book.getTicketId();
		
		FareRequest newfare = new FareRequest();	
		newfare.setBookingId(BId);
		newfare.setEmail(email);
		newfare.setFare(fare);
		
		bookService.saveFare(newfare);
		
		return null;

	}
	
	
	@GetMapping("/fare/{email1}")
	public ResponseEntity<FareRequest> getFareEmail(@PathVariable String email1){
	
		Optional<FareRequest> newFare = bookService.getByEmail(email1);
		if(newFare.isPresent()) {
			return ResponseEntity.ok(newFare.get());
		}else throw new ApiRequestException("Ticket not found");
		
	}
	
	
}
