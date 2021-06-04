package com.book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.book.model.FareRequest;
import com.book.model.ModelBook;
import com.book.repository.RepositoryBook;
import com.book.repository.RepositoryFare;

@Service
public class ServiceBooking {
	
	@Autowired
	RepositoryBook bookRepo;
	
	@Autowired
	RepositoryFare fareRepo;

	public ModelBook reserveTicket(ModelBook book) {
		
		return bookRepo.save(book);
	}
	
	public FareRequest saveFare(FareRequest Fare) {
		
		return fareRepo.save(Fare);
	}
	
	public Optional<FareRequest> getByEmail(String email){
		
		
		Optional<FareRequest> newFare = fareRepo.findFareByEmail(email);
		return newFare;
	}
}
