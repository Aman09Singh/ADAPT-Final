package com.train.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.train.model.ModelTripSchedule;
import com.train.repository.RepositoryTripSchedule;

@Service
public class ServiceTripSchedule {

	@Autowired
	RepositoryTripSchedule tripScheduleRepo;
	
	public Optional<ModelTripSchedule> createTripSchedule(ModelTripSchedule tripSchedule){
        return Optional.of(tripScheduleRepo.save(tripSchedule));
    }

    public Optional<ModelTripSchedule> updateTripSchedule(String tripScheduleID, ModelTripSchedule tripSchedule) {
        Optional<ModelTripSchedule> tripScheduleData = tripScheduleRepo.findById(tripScheduleID);
        if(tripScheduleData.isPresent()){
            System.out.println("service update " + tripSchedule);
            tripSchedule.setId(tripScheduleID);
            return Optional.of(tripScheduleRepo.save(tripSchedule));
        }
        return Optional.empty();
    }

    public Optional<ModelTripSchedule> deleteTripSchedule(String tripScheduleId){
        Optional<ModelTripSchedule> tripSchedule = tripScheduleRepo.findById(tripScheduleId);
        if (tripSchedule.isPresent()){
            tripScheduleRepo.deleteByTripId(tripScheduleId);
            return tripSchedule;
        }
        return Optional.empty();
    }

    public Optional<ModelTripSchedule> getTripSchedule(String tripScheduleId){
        return tripScheduleRepo.findById(tripScheduleId);
    }
    public ModelTripSchedule getTripScheduleByTripId(String tripId){
        return tripScheduleRepo.findByTripId(tripId);
    }

    public Optional<List<ModelTripSchedule>> getAll(){
    	return Optional.ofNullable(tripScheduleRepo.findAll());
    }
   
    public Optional<ModelTripSchedule> getTripScheduleByTripIdAndDate(String tripId, LocalDate date){
    	ModelTripSchedule byTripIdAndTripDate = tripScheduleRepo.findByTripIdAndTripDate(tripId, date);
        System.out.println("By" + byTripIdAndTripDate);
        return Optional.ofNullable(byTripIdAndTripDate);
    }

    public Optional<ModelTripSchedule> getTripScheduleByTripIdAndDate2(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
    	ModelTripSchedule tripScheduleByTripIdAndTripDate = tripScheduleRepo.findTripScheduleByTripIdAndTripDate(tripId, date);
        System.out.println("by2" + tripScheduleByTripIdAndTripDate);
        return Optional.ofNullable(tripScheduleByTripIdAndTripDate);
    }
    
    
}
