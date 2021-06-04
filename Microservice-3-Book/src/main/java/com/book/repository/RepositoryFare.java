package com.book.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.book.model.FareRequest;

public interface RepositoryFare extends MongoRepository<FareRequest, String>{

	@SuppressWarnings("unchecked")
	public FareRequest save(FareRequest fare);
	
	public Optional<FareRequest> findFareByEmail(String email);
}
