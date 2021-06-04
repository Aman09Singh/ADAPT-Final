package com.train.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.train.model.ModelTrip;

@Repository
public interface RepositoryTrip extends MongoRepository<ModelTrip, String>{

	
	public Optional<ModelTrip> findByTripId(String tripId);

    public void deleteByTripId(String tripId);

    public List<ModelTrip> findBySourceStationCodeAndDestinationStationCode(String srcStationCode, String destStationCode);
}
