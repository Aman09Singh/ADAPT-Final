package com.train.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.train.model.ModelTrain;

@Repository
public interface RepositoryTrain extends MongoRepository<ModelTrain, String>{
	
	public Optional<ModelTrain> findByNumber(String number);

    public void deleteById(String trainId);

    public List<ModelTrain> findTrainByFromStationCodeAndToStationCode(String fromStationCode, String toStationCode);

}
