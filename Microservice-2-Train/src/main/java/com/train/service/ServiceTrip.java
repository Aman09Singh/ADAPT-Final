package com.train.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.model.ModelTrip;
import com.train.repository.RepositoryTrip;

@Service
public class ServiceTrip {

	@Autowired
	RepositoryTrip tripRepo;
	
	public Optional<ModelTrip> createTrip(ModelTrip trip){
        return Optional.of(tripRepo.save(trip));
    }

    public Optional<ModelTrip> updateTrip(String tripId, ModelTrip trip){
        Optional<ModelTrip> tripData = tripRepo.findByTripId(tripId);
        if(tripData.isPresent()){
            trip.setTripId(tripId);
            return Optional.of(tripRepo.save(trip));
        }
        return Optional.empty();
    }

    public Optional<ModelTrip> deleteTrip(String tripId){
        Optional<ModelTrip> trip = tripRepo.findById(tripId);
        if(trip.isPresent()){
            tripRepo.deleteByTripId(tripId);
            return trip;
        }
        return Optional.empty();
    }

    public Optional<ModelTrip> getTrip(String tripId){
        return tripRepo.findByTripId(tripId);
    }

    public Optional<List<ModelTrip>> getAll(){
        return Optional.ofNullable(tripRepo.findAll());
    }

    public List<ModelTrip> getAlTripsBySrcAndDest(String srcStationCode, String destStationCode) {
        return tripRepo.findBySourceStationCodeAndDestinationStationCode(srcStationCode, destStationCode);
    }
}
