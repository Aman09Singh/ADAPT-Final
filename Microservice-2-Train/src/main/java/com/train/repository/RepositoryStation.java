package com.train.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.train.model.ModelStation;

@Repository
public interface RepositoryStation extends MongoRepository<ModelStation, String>{
	
	public void deleteByCode(String stationCode);

    public Optional<ModelStation> findByCode(String stationCode);

}
