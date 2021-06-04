package com.train.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.train.model.ModelTripSchedule;

@Repository
public interface RepositoryTripSchedule extends MongoRepository<ModelTripSchedule, String>{

	void deleteByTripId(String tripId);

    ModelTripSchedule findByTripId(String tipID);

    Optional<ModelTripSchedule> findById(String tipScheduleID);

    @Query("{$and:[{'tripId': ?0}, {'tripDate': ?1}]}")
    ModelTripSchedule findByTripIdAndTripDate(String tripId, LocalDate date);

    ModelTripSchedule findTripScheduleByTripIdAndTripDate(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date);
}
